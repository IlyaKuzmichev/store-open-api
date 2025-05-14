package edu.school21.store.controller.model.dto

import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.annotation.Validated

@Schema(description = "Address model", required = true)
@Validated
data class AddressDTO(
    var country: String,
    var city: String,
    var street: String,
)
