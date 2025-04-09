package com.coded.spring.ordering.users

import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository2 : JpaRepository<Order2, Long>

@Entity
@Table(name = "orders")
data class Order2(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val username: String,
    val restaurant: String,

    @Column(name = "items", columnDefinition = "text[]")
    val items: List<String>,

    ) {
    constructor() : this(null, "", "", emptyList())

}