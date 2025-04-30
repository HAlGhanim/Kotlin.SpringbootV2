package com.coded.spring.ordering.services

import com.coded.spring.ordering.repositories.UsersRepository
import com.coded.spring.ordering.serverCache
import jakarta.inject.Named

val usersCache = serverCache.getMap<String, List<User>>("users-cache")

@Named
class UsersProvider(
    private val usersRepository: UsersRepository
) {

    fun getUsers(): List<User> {
        val cachedUsers = usersCache["users"]
        if (!cachedUsers.isNullOrEmpty()) {
            println("Returning ${cachedUsers.size} users from cache.")
            return cachedUsers
        }

        println("No cached users found, calling API...")
        val users = usersRepository.findAll().map { User(
            id = it.id!!,
            name = it.name,
            username = it.username
        ) }
        usersCache.put("users", users)
        println("Cached ${users.size} users.")
        return users
    }
}
