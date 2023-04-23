package com.azathoth.handlistserver.user.auth

data class RegisterRequest(
    val nickname: String,
    val email: String,
    val password: String
)