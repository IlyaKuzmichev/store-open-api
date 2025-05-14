package edu.school21.store.controller.model.mapper

import edu.school21.store.controller.model.dto.SupplierDTO
import edu.school21.store.repository.entity.Supplier
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper
interface SupplierMapper {

    fun toDTO(supplier: Supplier): SupplierDTO

    @Mapping(target = "address.id", ignore = true)
    fun toEntity(supplierDTO: SupplierDTO): Supplier
}