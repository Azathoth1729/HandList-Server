package com.azathoth.handlistserver.user.auth

data class AuthRequest(
    val email: String,
    val password: String
)