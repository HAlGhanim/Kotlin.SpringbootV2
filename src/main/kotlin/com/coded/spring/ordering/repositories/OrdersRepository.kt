package com.coded.spring.ordering.repositories

import com.coded.spring.ordering.entities.OrderEntity
import jakarta.inject.Named
import org.springframework.data.jpa.repository.JpaRepository

@Named
interface OrdersRepository : JpaRepository<OrderEntity, Long>

