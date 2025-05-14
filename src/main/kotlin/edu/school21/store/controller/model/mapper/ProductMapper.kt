package edu.school21.store.controller.model.mapper

import edu.school21.store.controller.model.dto.ProductDTO
import edu.school21.store.repository.ImageRepository
import edu.school21.store.repository.SupplierRepository
import edu.school21.store.repository.entity.Image
import edu.school21.store.repository.entity.Product
import edu.school21.store.repository.entity.Supplier
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Named
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Mapper
abstract class ProductMapper {

    @Mapping(target = "supplierId", source = "supplier.id")
    @Mapping(target = "imageId", source = "image.id")
    abstract fun toDTO(product: Product): ProductDTO

    @Mapping(target = "supplier", source = "supplierId", qualifiedByName = ["mapSupplier"])
    @Mapping(target = "image", source = "imageId", qualifiedByName = ["mapImage"])
    abstract fun toEntity(dto: ProductDTO): Product

    @Named("mapSupplier")
    fun mapSupplier(supplierId: Long): Supplier = supplierRepository.findById(supplierId)
        .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Поставщик с id=$supplierId не найден") }

    @Named("mapImage")
    fun mapImage(imageId: UUID?): Image? {
        val id = imageId ?: return null
        return imageRepository.findById(id)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Изображение с id=$imageId не найдено") }
    }

    @Autowired
    lateinit var supplierRepository: SupplierRepository

    @Autowired
    lateinit var imageRepository: ImageRepository
}