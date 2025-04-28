package com.coded.spring.ordering.entities

import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*

@Entity
@Table(name = "orders")
data class OrderEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val restaurant: String? = null,

    @ManyToOne
    val user: UserEntity,

    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL], orphanRemoval = true)
    @JsonManagedReference
    var items: List<ItemEntity> = listOf()
) {
    constructor() : this(null, null, UserEntity(), listOf())
}
