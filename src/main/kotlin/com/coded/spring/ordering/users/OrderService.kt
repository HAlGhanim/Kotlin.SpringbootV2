package com.coded.spring.ordering.users

import jakarta.inject.Named

@Named
class OrderService(
    private val orderRepository2: OrderRepository2
) {

    fun listUsers(): List<Order> = orderRepository2.findAll().map {
        Order(
            username = it.username,
            restaurant = it.restaurant,
            items = it.items
        )
    }

    fun saveOrder(order: Order2): Order2 {
        return orderRepository2.save(order)
    }
}

data class Order(
    val username: String,
    val restaurant: String,
    val items: List<String>,
)