package com.azathoth.handlistserver.user.auth

import com.azathoth.handlistserver.config.AppConfig.Companion.API_VERSION
import com.azathoth.handlistserver.user.auth.AuthRequest
import com.azathoth.handlistserver.user.auth.AuthResponse
import com.azathoth.handlistserver.user.auth.RegisterRequest
import com.azathoth.handlistserver.user.auth.AuthService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/$API_VERSION/auth")
class AuthController(val service: AuthService) {
    @PostMapping("/register")
    fun register(@RequestBody request: RegisterRequest) =
        service.register(request)

    @PostMapping("/authenticate")
    fun authenticate(@RequestBody request: AuthRequest) =
        service.authenticate(request)
}