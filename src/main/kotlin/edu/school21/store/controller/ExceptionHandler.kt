package edu.school21.store.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDateTime

@ControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(ResponseStatusException::class)
    fun handleResponseStatusException(ex: ResponseStatusException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            timestamp = LocalDateTime.now(),
            status = ex.statusCode.value(),
            message = ex.reason ?: "Unknown error"
        )
        return ResponseEntity(errorResponse, ex.statusCode)
    }
}

data class ErrorResponse(
    val timestamp: LocalDateTime,
    val status: Int,
    val message: String,
)
