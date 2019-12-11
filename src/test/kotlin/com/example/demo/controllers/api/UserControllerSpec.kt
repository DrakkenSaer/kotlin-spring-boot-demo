package com.example.demo.controllers.api

import com.example.demo.models.User
import com.example.demo.repositories.ArticleRepository
import com.example.demo.repositories.UserRepository
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest
class UserControllerSpec(@Autowired val mockMvc: MockMvc) {

    @MockkBean
    lateinit var articleRepository: ArticleRepository

    @MockkBean
    lateinit var userRepository: UserRepository

    @Test
    fun `List users`() {
        val juergen = User("springjuergen", "Juergen", "Hoeller")
        val smaldini = User("smaldini", "Stéphane", "Maldini")
        every { userRepository.findAll() } returns listOf(juergen, smaldini)
        mockMvc.perform(get("/api/users/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("\$.[0].username").value(juergen.username))
                .andExpect(jsonPath("\$.[1].username").value(smaldini.username))
    }
}