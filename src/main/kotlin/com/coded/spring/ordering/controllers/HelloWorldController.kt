package com.coded.spring.ordering.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloWorldController(){

    @GetMapping("/hello")
    fun helloWorld(): String {
        return "Hello World"
    }

    @GetMapping("/boo")
    fun testing(): String {
        return "AAAA"
    }

    @PostMapping("/hello")
    fun helloName(@RequestBody req: Name) = "Hello ${req.name}!"
}

data class Name(
    val name:String
)