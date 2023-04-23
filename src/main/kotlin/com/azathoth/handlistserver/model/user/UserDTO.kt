package com.azathoth.handlistserver.model.user

import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated

data class UserDTO(
    val id: Long?,
    val email: String?,
    val nickname: String?,
    val role: UserRole?
)

fun User.toDTO() = UserDTO(
    id = this.id,
    email = this.email,
    nickname = this.nickname,
    role = this.role
)