package com.azathoth.handlistserver.controller

import com.azathoth.handlistserver.config.AppConfig.Companion.API_VERSION
import com.azathoth.handlistserver.model.auth.AuthRequest
import com.azathoth.handlistserver.model.auth.AuthResponse
import com.azathoth.handlistserver.model.auth.RegisterRequest
import com.azathoth.handlistserver.model.auth.AuthService
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