package com.example.demo.models

import com.example.demo.extensions.toSlug
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
class Article(
        var title: String,
        var headline: String,
        var content: String,
        @ManyToOne var author: User,
        var slug: String = title.toSlug(),
        @CreatedDate var createdDate: LocalDateTime = LocalDateTime.now(),
        @Id @GeneratedValue var id: UUID? = null
)