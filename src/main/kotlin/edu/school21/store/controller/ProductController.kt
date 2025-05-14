package edu.school21.store.controller

import edu.school21.store.controller.model.dto.ImageDTO
import edu.school21.store.controller.model.dto.ProductDTO
import edu.school21.store.service.ImageService
import edu.school21.store.service.ProductService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import jakarta.validation.constraints.Min
import org.springframework.core.io.ByteArrayResource
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "Products", description = "Operations with store products")
@RestController
@RequestMapping("/api/v1/products")
class ProductController(
    private val productService: ProductService,
    private val imageService: ImageService,
) {

    @Operation(summary = "Create new product")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Product created successfully")
        ]
    )
    @PostMapping
    fun addProduct(@Valid @RequestBody productDTO: ProductDTO) {
        productService.addProduct(productDTO)
    }

    @Operation(summary = "Reduce stock of the product")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Stock reduced successfully"),
            ApiResponse(responseCode = "400", description = "Stock less than requested quantity to reduce"),
            ApiResponse(responseCode = "404", description = "Product not found")
        ]
    )
    @PatchMapping("/{id}/reduce-stock")
    fun reduceStock(
        @PathVariable id: Long,
        @Valid @RequestParam(required = true) @Min(1) quantity: Int,
    ) =
        productService.reduceStock(id, quantity)

    @Operation(summary = "Get product by ID")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Product retrieved successfully"),
            ApiResponse(responseCode = "404", description = "Product not found")
        ]
    )
    @GetMapping("/{id}")
    fun getProductById(@PathVariable id: Long) = productService.getProductById(id)

    @Operation(summary = "Get all products")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Products retrieved successfully")

        ]
    )
    @GetMapping
    fun getAllProducts() = productService.getAllProducts()

    @Operation(summary = "Delete product by ID")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Product deleted successfully"),
            ApiResponse(responseCode = "404", description = "Product not found")
        ]
    )
    @DeleteMapping("/{id}")
    fun deleteProduct(@PathVariable id: Long) = productService.deleteProduct(id)

    @Operation(summary = "Get image by product ID")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Image retrieved successfully"),
            ApiResponse(responseCode = "404", description = "Product not found")
        ]
    )
    @GetMapping("/{productId}/image")
    fun getImageByProductId(@PathVariable productId: Long): ResponseEntity<ByteArrayResource> {
        val imageData = imageService.getImageByProductId(productId)

        return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"product-$productId.jpg\"")
            .body(ByteArrayResource(imageData))
    }

    @Operation(summary = "Add image to product")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Image added successfully"),
            ApiResponse(responseCode = "404", description = "Product not found")
        ]
    )
    @PostMapping("/{productId}/image")
    fun addImageToProduct(@PathVariable productId: Long, @RequestBody imageDTO: ImageDTO): ResponseEntity<Void> {
        imageService.addImageToProduct(imageDTO, productId)
        return ResponseEntity.ok().build()
    }
}