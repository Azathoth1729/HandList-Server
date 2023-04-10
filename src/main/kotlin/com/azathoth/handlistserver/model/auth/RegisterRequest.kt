package com.azathoth.handlistserver.model.auth

data class RegisterRequest(
    val username: String,
    val email: String,
    val password: String
)