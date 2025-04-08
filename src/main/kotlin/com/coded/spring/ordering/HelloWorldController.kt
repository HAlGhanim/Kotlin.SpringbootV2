package com.coded.spring.ordering

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloWorldController(val usersRepository: UsersRepository){

    @GetMapping("/api/hello")
    fun helloWorld() =  "Hello World"

    @PostMapping("/api/message")
    fun printMyMessage(@RequestBody request: PrintMyMessageRequest): String {
        return "your name is ${request.name} and civilId is ${request.civilId}"
    }

    @PostMapping("/api/my-name")
    fun sayMyName( @RequestBody request: SayMyNameRequest): String {
        usersRepository.save(User(name = request.name))
        return "Welcome ${request.name} !"
    }

    @GetMapping("/api/users")
    fun getAllUsers(): List<User> = usersRepository.findAll()
}
data class SayMyNameRequest(
    val name: String
)
data class PrintMyMessageRequest(
    val name: String,
    val civilId: String
)