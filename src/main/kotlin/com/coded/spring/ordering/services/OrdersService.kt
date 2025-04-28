package com.coded.spring.ordering.services

import com.coded.spring.ordering.controllers.OrdersController
import com.coded.spring.ordering.entities.ItemEntity
import com.coded.spring.ordering.entities.OrderEntity
import com.coded.spring.ordering.entities.UserEntity
import com.coded.spring.ordering.repositories.OrdersRepository
import com.coded.spring.ordering.repositories.UsersRepository
import jakarta.inject.Named

@Named
class OrdersService(
    private val usersRepository: UsersRepository,
    private val orderRepository: OrdersRepository
) {

    fun createOrder(req: OrdersController.OrderRequest): OrderResponse {
        val user = usersRepository.findById(req.user).orElseThrow {
            IllegalArgumentException("User with id ${req.user} not found")
        }

        val order = OrderEntity(
            restaurant = req.restaurant,
            user = user
        )

        val items = req.items.map { itemRequest ->
            ItemEntity(
                name = itemRequest.name,
                quantity = itemRequest.quantity,
                order = order // point to the parent order
            )
        }

        order.items = items

        val savedOrder = orderRepository.save(order)

        return OrderResponse(
            user = savedOrder.user,
            items = savedOrder.items
        )
    }
}

data class OrderResponse(
    val user: UserEntity,
    val items: List<ItemEntity>?
)
