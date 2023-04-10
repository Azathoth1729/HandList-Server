package com.azathoth.handlistserver.model.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
interface UserRepository : JpaRepository<User, Long> {
    fun findByEmail(email: String): User?
}