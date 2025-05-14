package edu.school21.store.controller

import edu.school21.store.controller.model.dto.AddressDTO
import edu.school21.store.controller.model.dto.ClientDTO
import edu.school21.store.service.ClientService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import jakarta.validation.constraints.Min
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@Tag(name = "Clients", description = "Operations with store clients")
@RestController
@RequestMapping("/api/v1/clients")
class ClientController(@Autowired private val clientService: ClientService) {

    @Operation(summary = "Create new client")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Client created successfully")
        ]
    )
    @PostMapping
    fun createClient(@Valid @RequestBody clientDTO: ClientDTO) {
        clientService.createClient(clientDTO)
    }

    @Operation(summary = "Delete client by ID")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Client deleted successfully"),
            ApiResponse(responseCode = "404", description = "Client not found")
        ]
    )
    @DeleteMapping("/{id}")
    fun deleteClient(@PathVariable id: Long) {
        clientService.deleteClient(id)
    }

    @Operation(summary = "Get clients by name and surname")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Clients retrieved successfully")
        ]
    )
    @GetMapping("/search")
    fun getClients(
        @RequestParam clientName: String,
        @RequestParam clientSurname: String,
    ): List<ClientDTO> {
        return clientService.getClientsByNameAndSurname(clientName, clientSurname)
    }

    @Operation(summary = "Get all clients with pagination")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Clients retrieved successfully"),
            ApiResponse(responseCode = "400", description = "Parameters validation failure")
        ]
    )
    @GetMapping
    fun getAllClients(
        @Valid @RequestParam(required = false) @Min(1) limit: Int?,
        @Valid @RequestParam(required = false) @Min(0) offset: Int?,
    ): List<ClientDTO> {
        return if (offset == null || limit == null) {
            clientService.getAllClients()
        } else {
            clientService.getAllClients(limit, offset)
        }
    }

    @Operation(summary = "Update client's address")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Client address updated successfully"),
            ApiResponse(responseCode = "404", description = "Client not found")
        ]
    )
    @PutMapping("/{id}/address")
    fun updateClientAddress(
        @PathVariable id: Long,
        @Valid @RequestBody addressDto: AddressDTO,
    ) {
        clientService.updateClientAddress(id, addressDto)
    }
}