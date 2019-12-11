package com.example.demo.repositories

import com.example.demo.models.Article
import org.springframework.data.repository.CrudRepository
import java.util.*

interface ArticleRepository : CrudRepository<Article, UUID> {
    fun findBySlug(slug: String): Article?
    fun findAllByOrderByCreatedDateDesc(): Iterable<Article>
}