package com.coded.spring.ordering

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import kotlin.test.assertEquals

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {

	@Autowired
	lateinit var restTemplate: TestRestTemplate

	@Test
	fun helloWorld() {
		val result = restTemplate.getForEntity("/hello", String::class.java)
		assertEquals(expected = HttpStatus.OK, actual = result?.statusCode)
		assertEquals(expected = "Hello World", actual = result.body)
	}

	@Test
	fun createUser() {
		val request = mapOf(
			"name" to "HelloUser",
			"age" to 18,
			"username" to "testuser",
			"password" to "password123",
			"role" to "USER"
		)

		val response = restTemplate.postForEntity("/users/v1/register", request, String::class.java)

		assertEquals(HttpStatus.OK, response.statusCode)


	}


}
