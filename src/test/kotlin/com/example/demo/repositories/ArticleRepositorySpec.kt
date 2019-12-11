package com.example.demo.repositories

import com.example.demo.models.Article
import com.example.demo.models.User
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.repository.findByIdOrNull

@DataJpaTest
class ArticleRepositorySpec @Autowired constructor(
        val entityManager: TestEntityManager,
        val articleRepository: ArticleRepository
) {

    @Test
    fun `findByIdOrNull returns Article`() {
        val user = User("username", "first", "last")
        entityManager.persist(user)
        val article = Article("Spring Framework 5.0 goes GA", "Dear Spring community ...", "Lorem ipsum", user)
        entityManager.persist(article)
        entityManager.flush()

        val found = articleRepository.findByIdOrNull(article.id!!)
        assertThat(found).isEqualTo(article)
    }

}