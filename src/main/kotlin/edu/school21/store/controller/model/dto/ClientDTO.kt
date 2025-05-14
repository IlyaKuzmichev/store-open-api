package edu.school21.store.controller.model.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.annotation.Nullable
import org.springframework.validation.annotation.Validated
import java.time.LocalDate

@Schema(description = "Client model, optional id and registrationDate", required = true)
@Validated
data class ClientDTO(
    @Nullable
    var id: Long?,
    var clientName: String,
    var clientSurname: String,
    var birthday: LocalDate,
    var gender: Char,
    @Nullable
    var registrationDate: LocalDate?,
    var address: AddressDTO,
)
