package com.coded.spring.ordering.controllers

import com.coded.spring.ordering.entities.Roles
import com.coded.spring.ordering.entities.UserEntity
import com.coded.spring.ordering.services.ProfilesService
import com.coded.spring.ordering.services.UsersService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import jakarta.validation.constraints.Pattern
import org.springframework.web.bind.annotation.*

@RestController
@Tag(name = "Users API", description = "Operations related to Users and Profiles")
class UsersControllers(
    private val usersService: UsersService,
    private val profileService: ProfilesService
) {

    @Operation(
        summary = "List all users",
        description = "Retrieve a list of all users",
        tags = ["Users API"]
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "List of all users",
                content = [Content(mediaType = "application/json")]
            )
        ]
    )
    @GetMapping("/users/v1/list")
    fun users() = usersService.listUsers()

    @Operation(
        summary = "List all profiles",
        description = "Retrieve a list of all user profiles",
        tags = ["Users API"]
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "List of all profiles",
                content = [Content(mediaType = "application/json")]
            )
        ]
    )
    @GetMapping("/users/profiles/v1/list")
    fun profiles() = profileService.listProfiles()

    @Operation(
        summary = "Get user by ID",
        description = "Retrieve a specific user by their ID",
        tags = ["Users API"]
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "User found",
                content = [Content(mediaType = "application/json")]
            ),
            ApiResponse(
                responseCode = "404",
                description = "User not found"
            )
        ]
    )
    @GetMapping("/users/v1/{userId}")
    fun user(@PathVariable userId: Long) = usersService.getUserDtoById(userId)

    @Operation(
        summary = "Register a new user",
        description = "Create a new user account",
        tags = ["Users API"]
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "User successfully created",
                content = [Content(mediaType = "application/json")]
            ),
            ApiResponse(
                responseCode = "400",
                description = "Invalid user data"
            )
        ]
    )
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

    @Operation(
        summary = "Create a user profile",
        description = "Create a profile for an existing user",
        tags = ["Users API"]
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Profile successfully created",
                content = [Content(mediaType = "application/json")]
            ),
            ApiResponse(
                responseCode = "400",
                description = "Invalid profile data"
            )
        ]
    )
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
