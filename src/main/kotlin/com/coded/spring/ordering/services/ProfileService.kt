package com.coded.spring.ordering.services

import com.coded.spring.ordering.entities.ProfileEntity
import com.coded.spring.ordering.repositories.ProfilesRepository
import com.coded.spring.ordering.repositories.UsersRepository
import org.springframework.stereotype.Service

@Service
class ProfilesService(
    private val profilesRepository: ProfilesRepository,
    private val usersRepository: UsersRepository
) {
    fun createProfileForUser(userId: Long, firstName: String, lastName: String, phoneNumber: String?) {
        val user = usersRepository.findById(userId).orElseThrow()
        val profile = ProfileEntity(
            user = user,
            firstName = firstName,
            lastName = lastName,
            phoneNumber = phoneNumber
        )
        profilesRepository.save(profile)

    }

    fun listProfiles(): List<Profile> = profilesRepository.findAll().map {
        Profile(
            id = it.id!!,
            firstName = it.firstName,
            lastName = it.lastName,
            phoneNumber = it.phoneNumber
        )
    }
}

data class Profile(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String?,
)
