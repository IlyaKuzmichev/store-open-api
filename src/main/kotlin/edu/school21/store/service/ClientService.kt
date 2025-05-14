package edu.school21.store.service

import edu.school21.store.controller.model.dto.AddressDTO
import edu.school21.store.controller.model.dto.ClientDTO
import edu.school21.store.controller.model.mapper.ClientMapper
import edu.school21.store.repository.ClientRepository
import jakarta.transaction.Transactional
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class ClientService(
    private val clientRepository: ClientRepository,
    private val clientMapper: ClientMapper,
    private val addressService: AddressService,
) {

    @Transactional
    fun createClient(clientDTO: ClientDTO): ClientDTO {
        val client = clientMapper.toEntity(clientDTO)
        client.address = addressService.getOrCreateAddress(
            clientDTO.address.country,
            clientDTO.address.city,
            clientDTO.address.street
        )
        val savedClient = clientRepository.save(client)
        return clientMapper.toDTO(savedClient)
    }

    @Transactional
    fun deleteClient(id: Long) {
        val client = findClientByIdInRepository(id)
        clientRepository.delete(client)
        addressService.removeUnusedAddress(client.address)
    }

    fun getClientsByNameAndSurname(clientName: String, clientSurname: String): List<ClientDTO> {
        val clients = clientRepository.findByClientNameAndClientSurname(clientName, clientSurname)
        return clients.map { clientMapper.toDTO(it) }
    }

    fun getAllClients(limit: Int?, offset: Int?): List<ClientDTO> {
        val pageable = PageRequest.of(offset ?: 0, limit ?: 0)
        val clients = clientRepository.findAll(pageable)
        return clients.get().map { clientMapper.toDTO(it) }.toList()
    }

    fun getAllClients(): List<ClientDTO> {
        val clients = clientRepository.findAll()
        return clients.map { clientMapper.toDTO(it) }
    }

    @Transactional
    fun updateClientAddress(id: Long, addressDTO: AddressDTO): ClientDTO {
        val client = findClientByIdInRepository(id)
        val oldAddress = client.address
        client.address = addressService.getOrCreateAddress(
            addressDTO.country,
            addressDTO.city,
            addressDTO.street
        )
        val updatedClient = clientRepository.save(client)
        addressService.removeUnusedAddress(oldAddress)
        return clientMapper.toDTO(updatedClient)
    }

    private fun findClientByIdInRepository(id: Long) =
        clientRepository.findById(id).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND, "Client with id=$id not found")
        }
}