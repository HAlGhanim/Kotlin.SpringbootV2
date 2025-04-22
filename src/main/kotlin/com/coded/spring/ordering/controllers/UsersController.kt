package com.coded.spring.ordering.controllers

import com.coded.spring.ordering.entities.Roles
import com.coded.spring.ordering.entities.UserEntity
import com.coded.spring.ordering.services.ProfilesService
import com.coded.spring.ordering.services.UsersService
import jakarta.validation.Valid
import jakarta.validation.constraints.Pattern
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UsersControllers(private val usersService: UsersService, private val profileService: ProfilesService) {

    @GetMapping("/users/v1/list")
    fun users() = usersService.listUsers()

    @GetMapping("/users/profiles/v1/list")
    fun profiles() = profileService.listProfiles()

    @GetMapping("/users/v1/{userId}")
    fun user(@PathVariable userId: Long) = usersService.getUserDtoById(userId)

    @PostMapping("/users/v1/register")
    fun createUser(@RequestBody request: UserRequest) {
        val user = UserEntity(
            name = request.name,
            age = request.age,
            username = request.username,
            password = request.password,
            role = request.role
        )
        usersService.createUser(user)
    }

    @PostMapping("/users/v1/{userId}/profile")
    fun createProfile(
        @RequestBody @Valid request: ProfileRequest,
        @PathVariable userId: Long
    ) {
        profileService.createProfileForUser(
            userId = userId,
            firstName = request.firstName,
            lastName = request.lastName,
            phoneNumber = request.phoneNumber
        )
    }

    data class UserRequest(
        val name: String,
        val age: Int,
        val username: String,
        val password: String,
        val role: Roles
    )

    data class ProfileRequest(

        @field:Pattern(regexp = "^[a-zA-Z]+$", message = "First name must contain only letters")
        val firstName: String,

        @field:Pattern(regexp = "^[a-zA-Z]+$", message = "Last name must contain only letters")
        val lastName: String,

        @field:Pattern(regexp = "^\\d{8}$", message = "Phone number must be exactly 8 digits with no letters")
        val phoneNumber: String?
    )


}