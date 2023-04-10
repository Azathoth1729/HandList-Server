package com.azathoth.handlistserver.model.auth

import com.azathoth.handlistserver.config.security.JwtService
import com.azathoth.handlistserver.model.user.User
import com.azathoth.handlistserver.model.user.UserRepository
import com.azathoth.handlistserver.model.user.UserRole
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val userRepo: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtService: JwtService,
    private val authenticationManager: AuthenticationManager
) {
    fun register(request: RegisterRequest): AuthResponse {
        val user = User(
            username = request.username,
            email = request.email,
            password = passwordEncoder.encode(request.password),
            role = UserRole.USER
        )
        userRepo.save(user)

        val jwtToken = jwtService.generateToken(user)

        return AuthResponse(
            accessToken = jwtToken
        )
    }

    fun authenticate(request: AuthRequest): AuthResponse {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                request.email,
                request.password
            )
        )
        val user = userRepo.findByEmail(request.email) ?: throw UsernameNotFoundException("${request.email} not found")
        val jwtToken = jwtService.generateToken(user)

        return AuthResponse(
            accessToken = jwtToken
        )
    }
}