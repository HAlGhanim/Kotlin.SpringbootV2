package com.coded.spring.ordering.entities

import jakarta.persistence.*


@Entity
@Table(name="items")
data class ItemEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String,
    val quantity: Int,
    val order_id: Long
){
    constructor() : this(null, "", 0, 0)
}