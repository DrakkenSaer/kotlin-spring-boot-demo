package com.example.demo.repositories

import com.example.demo.models.User
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager

@DataJpaTest
class UserRepositorySpec @Autowired constructor(
        val entityManager: TestEntityManager,
        val userRepository: UserRepository
) {

    @Test
    fun `findByUsername returns User`() {
        val user = User("username", "firstname", "lastname")
        entityManager.persist(user)
        entityManager.flush()
        val found = userRepository.findByUsername(user.username);
        assertThat(found).isEqualTo(user)
    }
}