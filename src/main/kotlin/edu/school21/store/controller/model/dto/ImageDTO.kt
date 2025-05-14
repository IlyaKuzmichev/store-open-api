package edu.school21.store.controller.model.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.annotation.Nullable
import org.springframework.validation.annotation.Validated
import java.util.*

@Schema(description = "Image model, optional UUID", required = true)
@Validated
data class ImageDTO(
    @Nullable
    var id: UUID?,
    @Schema(description = "Image in Base64 format")
    var image: String,
)
