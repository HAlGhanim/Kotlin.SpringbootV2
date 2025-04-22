package com.coded.spring.ordering.entities

import com.coded.spring.ordering.entities.UserEntity
import jakarta.persistence.*

@Entity
@Table(name = "profiles")
data class ProfileEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    val user: UserEntity,

    val firstName: String,
    val lastName: String,
    val phoneNumber: String?
) {
    constructor() : this(null, UserEntity(),"","",null)
}
