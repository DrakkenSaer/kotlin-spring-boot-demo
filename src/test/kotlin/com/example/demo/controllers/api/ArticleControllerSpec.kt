package com.example.demo.controllers.api

import com.example.demo.models.Article
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
class ArticleControllerSpec(@Autowired val mockMvc: MockMvc) {

    @MockkBean
    lateinit var userRepository: UserRepository

    @MockkBean
    lateinit var articleRepository: ArticleRepository

    @Test
    fun `List articles`() {
        val juergen = User("springjuergen", "Juergen", "Hoeller")
        val spring5Article = Article("Spring Framework 5.0 goes GA", "Dear Spring community ...", "Lorem ipsum", juergen)
        val spring43Article = Article("Spring Framework 4.3 goes GA", "Dear Spring community ...", "Lorem ipsum", juergen)
        every { articleRepository.findAllByOrderByCreatedDateDesc() } returns listOf(spring5Article, spring43Article)
        mockMvc.perform(get("/api/articles/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("\$.[0].author.username").value(juergen.username))
                .andExpect(jsonPath("\$.[0].slug").value(spring5Article.slug))
                .andExpect(jsonPath("\$.[1].author.username").value(juergen.username))
                .andExpect(jsonPath("\$.[1].slug").value(spring43Article.slug))
    }
}