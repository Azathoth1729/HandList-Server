package com.azathoth.handlistserver.model.auth

data class RegisterRequest(
    val nickname: String,
    val email: String,
    val password: String
)