package edu.school21.store.controller.model.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.annotation.Nullable
import org.springframework.validation.annotation.Validated

@Schema(description = "Supplier model, optional id", required = true)
@Validated
data class SupplierDTO(
    @Nullable
    var id: Long?,
    var name: String,
    var address: AddressDTO,
    var phoneNumber: String,
)
