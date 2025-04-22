package com.coded.spring.ordering.repositories

import com.coded.spring.ordering.entities.ProfileEntity
import com.coded.spring.ordering.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ProfilesRepository : JpaRepository<ProfileEntity, Long> {
    fun existsByUser(user: UserEntity): Boolean
}
