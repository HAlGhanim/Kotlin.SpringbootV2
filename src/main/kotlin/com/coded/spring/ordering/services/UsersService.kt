package com.coded.spring.ordering.services

import com.coded.spring.ordering.entities.UserEntity
import com.coded.spring.ordering.repositories.UsersRepository
import jakarta.inject.Named

@Named
class UsersService(
    private val usersRepository: UsersRepository,
) {

    fun getUserById(userId: Long): UserEntity {
        return usersRepository.findById(userId).orElseThrow()
    }

    fun getUserDtoById(userId: Long): User {
        val user = usersRepository.findById(userId).orElseThrow()
        return User(
            id = user.id!!,
            name = user.name,
            username = user.username
        )
    }


    fun listUsers(): List<User> = usersRepository.findAll().map {
        User(
            id = it.id!!,
            name = it.name,
            username = it.username,
        )
    }

    fun createUser(user: UserEntity): UserEntity {
        return usersRepository.save(user)
    }
}

data class User(
    val id: Long,
    val name: String,
    val username: String,
)