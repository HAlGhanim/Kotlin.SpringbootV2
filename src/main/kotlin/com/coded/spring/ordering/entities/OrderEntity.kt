package com.coded.spring.ordering.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "orders")
data class OrderEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val restaurant:String? =null,

    @ManyToOne
    val user: UserEntity,


    @OneToMany(mappedBy = "order_id")
    val items: List<ItemEntity>? = null
){
    constructor() : this(null,null, UserEntity(), listOf())
}