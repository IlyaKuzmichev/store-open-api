package edu.school21.store.service

import edu.school21.store.controller.model.dto.ProductDTO
import edu.school21.store.controller.model.mapper.ProductMapper
import edu.school21.store.repository.ProductRepository
import edu.school21.store.repository.entity.Product
import jakarta.transaction.Transactional
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDate

@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val productMapper: ProductMapper,
) {

    fun addProduct(productDTO: ProductDTO): ProductDTO {
        val product = productMapper.toEntity(productDTO)
        val savedProduct = productRepository.save(product)
        return productMapper.toDTO(savedProduct)
    }

    @Transactional
    fun reduceStock(productId: Long, quantity: Int) {
        val product = getProductByIdFromRepository(productId)

        if (product.availableStock < quantity) {
            throw ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Insufficient quantity of products (current balance: ${product.availableStock})"
            )
        }

        product.availableStock -= quantity
        product.lastUpdateDate = LocalDate.now()
        productRepository.save(product)
    }

    fun getProductById(productId: Long): ProductDTO =
        productMapper.toDTO(
            getProductByIdFromRepository(productId)
        )

    fun getAllProducts() = productRepository.findAll().map { productMapper.toDTO(it) }

    @Transactional
    fun deleteProduct(id: Long) =
        productRepository.delete(
            getProductByIdFromRepository(id)
        )

    fun getProductByIdFromRepository(productId: Long): Product =
        productRepository.findById(productId).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND, "Product with id=$productId not found")
        }
}