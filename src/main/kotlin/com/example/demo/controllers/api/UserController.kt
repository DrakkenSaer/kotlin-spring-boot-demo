package com.example.demo.controllers.api

import com.example.demo.repositories.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/users")
class UserController(private val userRepository: UserRepository) {

    @GetMapping("/")
    fun findAll() = userRepository.findAll()

    @GetMapping("/{username}")
    fun findByUsername(@PathVariable username: String) =
            userRepository.findByUsername(username) ?: ResponseStatusException(HttpStatus.NOT_FOUND, "This user does not exist")
}