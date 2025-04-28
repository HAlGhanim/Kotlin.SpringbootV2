package com.coded.spring.ordering.controllers

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal


@RestController
@Tag(name = "Hello API", description = "Operations related to Hello")
class HelloWorldController(
    @Value("\${server-welcome-message}")
    val welcomeMessage: String,
    @Value("\${eid-message}")
    val eidMessage: String,
    @Value("\${feature.eid.enabled}")
    private val eidEnabled: Boolean,
    @Value("\${discount}")
    val discount: Boolean
) {

    @Operation(summary = "Say Hello World")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successful Hello World response")
        ]
    )
    @GetMapping("/hello")
    fun helloWorld(): String {
        return if(eidEnabled){
            eidMessage
        }
        else {
            welcomeMessage
        }
    }

    @Operation(summary = "Testing endpoint")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successful test response")
        ]
    )
    @GetMapping("/boo")
    fun testing(): String {
        return "AAAA"
    }

    @Operation(summary = "Say Hello with Name")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successful personalized Hello response")
        ]
    )
    @PostMapping("/hello")
    fun helloName(@RequestBody req: Name): String {
        return if(discount){
            "Hello ${req.name}, The discounted price is: ${req.price * BigDecimal("0.8")}!"
        }
        else {
            "Hello ${req.name}, The Price is: ${req.price}!"
        }
    }
}

data class Name(
    val name: String,
    val price: BigDecimal = BigDecimal("100.0")
)
