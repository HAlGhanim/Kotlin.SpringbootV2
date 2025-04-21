package com.coded.spring.ordering.authentication

import com.coded.spring.ordering.repositories.UsersRepository
import org.springframework.security.core.userdetails.*
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    private val usersRepository: UsersRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val user = usersRepository.findByUsername(username)
            ?: throw UsernameNotFoundException("User not found")

        return User.builder()
            .username(user.username)
            .password(user.password)
            .roles(user.role.toString())
            .build()
    }
}