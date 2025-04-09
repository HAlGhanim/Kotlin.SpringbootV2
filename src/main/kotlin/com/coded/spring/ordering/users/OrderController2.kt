package com.coded.spring.ordering.users

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderController2(private val orderService: OrderService ){

    @GetMapping("/api/get-orders2")
    fun listOrders(): List<Order> {
        return orderService.listUsers()
    }

    @PostMapping("/api/orders2")
    fun sayMyName( @RequestBody request: OrderRequest): Order2 {
        val order = Order2(
            username = request.username,
            restaurant = request.restaurant,
            items = request.items,)
        return orderService.saveOrder(order)
    }

    data class OrderRequest(
        val username: String,
        val restaurant: String,
        val items: List<String>,
    )
}