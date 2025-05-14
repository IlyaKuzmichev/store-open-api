package edu.school21.store.controller.model.mapper

import edu.school21.store.controller.model.dto.AddressDTO
import edu.school21.store.repository.entity.Address
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper
interface AddressMapper {

    fun toDTO(address: Address): AddressDTO

    @Mapping(target = "id", ignore = true)
    fun toEntity(addressDTO: AddressDTO): Address
}