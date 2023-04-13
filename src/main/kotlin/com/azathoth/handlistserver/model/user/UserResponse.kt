package com.azathoth.handlistserver.model.user

import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated

data class UserResponse(
    val email: String,
    val nickname: String,
    @Enumerated(EnumType.STRING)
    val role: UserRole
)