package edu.school21.store.service

import edu.school21.store.controller.model.dto.ImageDTO
import edu.school21.store.repository.ImageRepository
import edu.school21.store.repository.ProductRepository
import edu.school21.store.repository.entity.Image
import jakarta.transaction.Transactional
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Service
class ImageService(
    private val imageRepository: ImageRepository,
    private val productRepository: ProductRepository,
) {

    @Transactional
    fun addImageToProduct(imageDTO: ImageDTO, productId: Long) {
        val product = findProductInRepositoryById(productId)
        val image = Image(image = decodeBase64Image(imageDTO.image))
        val savedImage = imageRepository.save(image)

        product.image = savedImage
        productRepository.save(product)
    }

    @Transactional
    fun updateImage(imageDTO: ImageDTO) {
        val image = findImageInRepositoryById(
            imageDTO.id
                ?: throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Image ID must not be NULL")
        )
        image.image = decodeBase64Image(imageDTO.image)

        imageRepository.save(image)
    }

    @Transactional
    fun deleteImage(imageId: UUID) =
        imageRepository.delete(
            findImageInRepositoryById(imageId)
        )

    fun getImageByProductId(productId: Long): ByteArray {
        val product = findProductInRepositoryById(productId)

        val image = product.image ?: throw ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Продукт с id=$productId не содержит изображения"
        )

        return image.image
    }

    fun getImageById(imageId: UUID) = findImageInRepositoryById(imageId).image

    private fun findImageInRepositoryById(imageId: UUID) =
        imageRepository.findById(imageId)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Image with id=$imageId not found") }

    private fun findProductInRepositoryById(productId: Long) =
        productRepository.findById(productId)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Product with  id=$productId not found") }

    private fun decodeBase64Image(base64String: String): ByteArray {
        return Base64.getDecoder().decode(base64String)
    }
}