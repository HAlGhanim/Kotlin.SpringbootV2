package com.coded.spring.ordering.services

import com.coded.spring.ordering.authentication.jwt.JwtService
import jakarta.inject.Named
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.exchange


@Named
class UsersClient(
    private val jwtService: JwtService
) {
    fun getUsers(): List<User> {
        val restTemplate = RestTemplate()
        val url = "http://localhost:8080/users/v1/list"

        val headers = HttpHeaders()
        headers.setBearerAuth(jwtService.generateToken("testuser")
        )

        val entity = HttpEntity<Void>(headers)

        val response = restTemplate.exchange<List<User>>(
            url = url,
            method = HttpMethod.GET,
            requestEntity = entity,
            object : ParameterizedTypeReference<List<User>>() {}
        )

        return response.body ?: listOf()
    }
}
