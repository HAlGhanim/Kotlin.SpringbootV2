package com.coded.spring.ordering.entities

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*

@Entity
@Table(name = "items")
data class ItemEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val name: String,

    val quantity: Int,

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    @JsonBackReference  // ✅ Add this
    var order: OrderEntity? = null
) {
    constructor() : this(null, "", 0, null)
}
