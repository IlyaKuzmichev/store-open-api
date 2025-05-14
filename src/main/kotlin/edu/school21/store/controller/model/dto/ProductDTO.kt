package edu.school21.store.controller.model.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.annotation.Nullable
import org.springframework.validation.annotation.Validated
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

@Schema(description = "Product model, optional id, lastUpdateDate, imageId", required = true)
@Validated
data class ProductDTO(
    @Nullable
    var id: Long?,
    var name: String,
    var category: String,
    var price: BigDecimal,
    var availableStock: Int,
    @Nullable
    var lastUpdateDate: LocalDate?,
    var supplierId: Long,
    @Nullable
    var imageId: UUID?,
)
