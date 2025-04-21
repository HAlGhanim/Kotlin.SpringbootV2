package com.coded.spring.ordering.services

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


    fun createOrder(userId: Long): OrderResponse {
        val user = usersRepository.findById(userId).get()
        val newOrder = OrderEntity(user = user)
        orderRepository.save(newOrder)
        return OrderResponse(newOrder.user, newOrder.items)
    }

}

data class OrderResponse(
    val user: UserEntity,
    val items: List<ItemEntity>?
)