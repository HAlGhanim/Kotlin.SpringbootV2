package com.coded.spring.ordering

import com.coded.spring.ordering.authentication.jwt.JwtService
import com.coded.spring.ordering.entities.UserEntity
import com.coded.spring.ordering.repositories.UsersRepository
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.test.context.ActiveProfiles
import kotlin.test.assertEquals

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ApplicationTests {

	companion object {
		lateinit var savedUser: UserEntity

		@JvmStatic
		@BeforeAll
		fun setUp(
			@Autowired usersRepository: UsersRepository,
			@Autowired passwordEncoder: PasswordEncoder,
		) {
			usersRepository.deleteAll()
			savedUser = usersRepository.save(
				UserEntity(
					name = "coded",
					age = 10,
					username = "coded",
					password = passwordEncoder.encode("joincoded")
				)
			)
		}
	}

	@Autowired
	lateinit var restTemplate: TestRestTemplate

	@Test
	fun testHelloWorld(@Autowired jwtService: JwtService) {
		val token = jwtService.generateToken("coded")
		val headers = HttpHeaders()
		headers.setBearerAuth(token)

		val requestEntity = HttpEntity<String>(headers)

		val result = restTemplate.exchange(
			"/hello",
			HttpMethod.GET,
			requestEntity,
			String::class.java
		)
		assertEquals(HttpStatus.OK, result.statusCode)
		assertEquals("Hello World!", result.body)
	}

	@Test
	fun testOrderSubmit(@Autowired jwtService: JwtService) {
		val token = jwtService.generateToken("coded")
		val headers = HttpHeaders()
		headers.setBearerAuth(token)
		headers.set("Content-Type", "application/json")

		val requestBody = mapOf(
			"user" to savedUser.id!!,
			"restaurant" to "Testaurant",
			"items" to listOf(
				mapOf("name" to "Chicken Burger", "quantity" to 3)
			)
		)

		val requestEntity = HttpEntity(requestBody, headers)
		val response = restTemplate.exchange(
			"/orders/v1/orders",
			HttpMethod.POST,
			requestEntity,
			String::class.java
		)

		assertEquals(HttpStatus.OK, response.statusCode)
	}

	@Test
	fun testProfileSubmit(@Autowired jwtService: JwtService) {
		val token = jwtService.generateToken("coded")
		val headers = HttpHeaders()
		headers.setBearerAuth(token)
		headers.set("Content-Type", "application/json")

		val requestBody = mapOf(
			"firstName" to "Humoud",
			"lastName" to "AlGhanim",
			"phoneNumber" to "99996703"
		)

		val requestEntity = HttpEntity(requestBody, headers)
		val response = restTemplate.exchange(
			"/users/v1/${savedUser.id}/profile",
			HttpMethod.POST,
			requestEntity,
			String::class.java
		)

		assertEquals(HttpStatus.OK, response.statusCode)
	}
}
