package edu.school21.store.controller.model.mapper

import edu.school21.store.controller.model.dto.ClientDTO
import edu.school21.store.repository.entity.Client
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper
interface ClientMapper {

    fun toDTO(client: Client): ClientDTO

    @Mapping(target = "address.id", ignore = true)
    fun toEntity(clientDTO: ClientDTO): Client
}
