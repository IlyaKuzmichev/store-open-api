package edu.school21.store.controller

import edu.school21.store.controller.model.dto.AddressDTO
import edu.school21.store.controller.model.dto.SupplierDTO
import edu.school21.store.service.SupplierService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@Tag(name = "Suppliers", description = "Operations with suppliers of products")
@RestController
@RequestMapping("/api/v1/suppliers")
class SupplierController(private val supplierService: SupplierService) {

    @Operation(summary = "Create new supplier")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Supplier created successfully")
        ]
    )
    @PostMapping
    fun addSupplier(@Valid @RequestBody supplierDTO: SupplierDTO) {
        supplierService.addSupplier(supplierDTO)
    }

    @Operation(summary = "Update supplier's address")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Supplier address updated successfully"),
            ApiResponse(responseCode = "404", description = "Supplier not found")
        ]
    )
    @PutMapping("/{id}/address")
    fun updateSupplierAddress(@PathVariable id: Long, @RequestBody addressDTO: AddressDTO) {
        supplierService.updateSupplierAddress(id, addressDTO)
    }

    @Operation(summary = "Delete supplier by ID")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Supplier deleted successfully"),
            ApiResponse(responseCode = "404", description = "Supplier not found")
        ]
    )
    @DeleteMapping("/{id}")
    fun deleteSupplier(@PathVariable id: Long) =
        supplierService.deleteSupplier(id)

    @Operation(summary = "Get all suppliers")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Suppliers retrieved successfully")
        ]
    )
    @GetMapping
    fun getAllSuppliers() = supplierService.getAllSuppliers()

    @Operation(summary = "Get supplier by ID")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Supplier retrieved successfully"),
            ApiResponse(responseCode = "404", description = "Supplier not found")
        ]
    )
    @GetMapping("/{id}")
    fun getSupplierById(@PathVariable id: Long) =
        supplierService.getSupplierById(id)
}