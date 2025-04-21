package com.coded.spring.ordering.controllers

import com.coded.spring.ordering.entities.ItemEntity
import com.coded.spring.ordering.entities.OrderEntity
import com.coded.spring.ordering.repositories.OrdersRepository
import com.coded.spring.ordering.services.OrdersService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


@RestController

class OrdersController(val ordersRepository: OrdersRepository, val ordersService: OrdersService) {

    @PostMapping("/orders")
    fun createNewOrder(@RequestBody req: OrderRequest) {
        ordersService.createOrder(userId = req.user)
    }

    @GetMapping("/orders")
    fun getOrders(): MutableList<OrderEntity> {
        return ordersRepository.findAll()
    }


    data class OrderRequest(
        val user: Long,
        val restaurant: String,
        val items: List<ItemEntity>
    )
}
