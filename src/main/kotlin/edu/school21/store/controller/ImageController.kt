package edu.school21.store.controller

import edu.school21.store.controller.model.dto.ImageDTO
import edu.school21.store.service.ImageService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.core.io.ByteArrayResource
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@Tag(name = "Images", description = "Operations with images of products")
@RestController
@RequestMapping("/api/v1/images")
class ImageController(private val imageService: ImageService) {

    @Operation(summary = "Update image")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Image updated successfully"),
            ApiResponse(responseCode = "400", description = "Image ID must not be null"),
            ApiResponse(responseCode = "404", description = "Image not found")
        ]
    )
    @PutMapping
    fun updateImage(@RequestBody imageDTO: ImageDTO): ResponseEntity<Void> {
        imageService.updateImage(imageDTO)
        return ResponseEntity.ok().build()
    }

    @Operation(summary = "Delete image by ID")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Image deleted successfully"),
            ApiResponse(responseCode = "404", description = "Image not found")
        ]
    )
    @DeleteMapping("/{id}")
    fun deleteImage(@PathVariable id: UUID): ResponseEntity<Void> {
        imageService.deleteImage(id)
        return ResponseEntity.ok().build()
    }

    @Operation(summary = "Get image by ID")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Image retrieved successfully"),
            ApiResponse(responseCode = "404", description = "Image not found")
        ]
    )
    @GetMapping("/{id}")
    fun getImageById(@PathVariable id: UUID): ResponseEntity<ByteArrayResource> {
        val imageData = imageService.getImageById(id)

        return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"image.jpg\"")
            .body(ByteArrayResource(imageData))
    }
}