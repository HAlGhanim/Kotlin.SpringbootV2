package com.coded.spring.ordering.controllers

import com.coded.spring.ordering.entities.ItemEntity
import com.coded.spring.ordering.entities.OrderEntity
import com.coded.spring.ordering.repositories.OrdersRepository
import com.coded.spring.ordering.services.OrdersService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

@RestController
@Tag(name = "Orders API", description = "Operations related to Orders")
class OrdersController(
    val ordersRepository: OrdersRepository,
    val ordersService: OrdersService,
) {

    @Operation(
        summary = "Create a new order",
        description = "Creates a new order for a specific user",
        tags = ["Orders API"]
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Order successfully created",
                content = [Content(mediaType = "application/json")]
            ),
            ApiResponse(
                responseCode = "400",
                description = "Invalid order data"
            )
        ]
    )
    @PostMapping("/orders/v1/orders")
    fun createNewOrder(@RequestBody req: OrderRequest) {
        ordersService.createOrder(req)
    }

    @Operation(
        summary = "Retrieve all orders",
        description = "Get a list of all existing orders",
        tags = ["Orders API"]
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "List of all orders",
                content = [Content(mediaType = "application/json")]
            )
        ]
    )
    @GetMapping("/orders")
    fun getOrders(): MutableList<OrderEntity> {
        return ordersRepository.findAll()
    }

    data class OrderRequest(
        val user: Long,
        val restaurant: String,
        val items: List<ItemRequest>
    )

    data class ItemRequest(
        val name: String,
        val quantity: Int
    )
}
