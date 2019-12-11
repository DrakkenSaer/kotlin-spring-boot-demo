package com.example.demo.controllers

import com.example.demo.DemoProperties
import com.example.demo.repositories.ArticleRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.server.ResponseStatusException

@Controller
class HtmlController(
        private val articleRepository: ArticleRepository,
        private val demoProperties: DemoProperties
) {

    @GetMapping("/")
    fun demo(model: Model): String {
        model["title"] = demoProperties.title
        model["banner"] = demoProperties.banner
        model["articles"] = articleRepository.findAllByOrderByCreatedDateDesc()
        return "demo"
    }

    @GetMapping("/articles/{slug}")
    fun getBySlug(@PathVariable slug: String, model: Model): String {
        val article = articleRepository.findBySlug(slug) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "This article does not exist")
        model["title"] = article.title
        model["article"] = article
        return "article"
    }
}
