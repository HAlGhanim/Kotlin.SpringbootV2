package com.coded.spring.ordering.users

import jakarta.inject.Named

@Named
class UsersService(
    private val usersRepository: UsersRepository2,
) {

    fun listUsers(): List<User> = usersRepository.findAll().map {
        User(
            name = it.name,
            age = it.age
        )
    }
}

data class User(
    val name: String,
    val age: Int
)