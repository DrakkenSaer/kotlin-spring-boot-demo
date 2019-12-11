package com.example.demo.repositories

import com.example.demo.models.User
import org.springframework.data.repository.CrudRepository
import java.util.*

interface UserRepository : CrudRepository<User, UUID> {
    fun findByUsername(username: String): User?
}