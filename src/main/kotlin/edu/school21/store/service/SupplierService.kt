package edu.school21.store.service

import edu.school21.store.controller.model.dto.AddressDTO
import edu.school21.store.controller.model.dto.SupplierDTO
import edu.school21.store.controller.model.mapper.SupplierMapper
import edu.school21.store.repository.SupplierRepository
import jakarta.transaction.Transactional
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class SupplierService(
    private val supplierRepository: SupplierRepository,
    private val supplierMapper: SupplierMapper,
    private val addressService: AddressService,
) {

    @Transactional
    fun addSupplier(supplierDTO: SupplierDTO): SupplierDTO {
        val supplier = supplierMapper.toEntity(supplierDTO)
        supplier.address = addressService.getOrCreateAddress(
            supplierDTO.address.country,
            supplierDTO.address.city,
            supplierDTO.address.street
        )
        val savedSupplier = supplierRepository.save(supplier)
        return supplierMapper.toDTO(savedSupplier)
    }

    @Transactional
    fun updateSupplierAddress(id: Long, addressDTO: AddressDTO): SupplierDTO {
        val supplier = findSupplierByIdInRepository(id)
        val oldAddress = supplier.address

        supplier.address = addressService.getOrCreateAddress(
            addressDTO.country,
            addressDTO.city,
            addressDTO.street
        )
        val updatedSupplier = supplierRepository.save(supplier)
        addressService.removeUnusedAddress(oldAddress)
        return supplierMapper.toDTO(updatedSupplier)
    }

    @Transactional
    fun deleteSupplier(id: Long) {
        val supplier = findSupplierByIdInRepository(id)
        supplierRepository.delete(supplier)
        addressService.removeUnusedAddress(supplier.address)
    }

    fun getAllSuppliers() = supplierRepository.findAll().map { supplierMapper.toDTO(it) }

    fun getSupplierById(id: Long) =
        supplierMapper.toDTO(findSupplierByIdInRepository(id))

    private fun findSupplierByIdInRepository(id: Long) =
        supplierRepository.findById(id).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND, "Supplier with id $id not found")
        }
}