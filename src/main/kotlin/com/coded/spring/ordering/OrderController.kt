package com.coded.spring.ordering

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderController(val orderRepository: OrderRepository){

    @GetMapping("/api/get-orders")
    fun listOrders(): List<Order> {
        return orderRepository.findAll()
    }

    @PostMapping("/api/orders")
    fun sayMyName( @RequestBody request: OrderRequest): Order {
        val order = Order(
            username = request.username,
            restaurant = request.restaurant,
            items = request.items,)
        return orderRepository.save(order)
    }

    data class OrderRequest(
        val username: String,
        val restaurant: String,
        val items: List<String>,
    )
}