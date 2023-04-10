package com.azathoth.handlistserver.model.auth

data class AuthRequest(
    val email: String,
    val password: String
)