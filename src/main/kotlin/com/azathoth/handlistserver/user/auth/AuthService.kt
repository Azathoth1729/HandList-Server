package com.azathoth.handlistserver.user.auth

import com.azathoth.handlistserver.security.JwtService
import com.azathoth.handlistserver.user.model.User
import com.azathoth.handlistserver.user.UserRepository
import com.azathoth.handlistserver.user.model.UserRole
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
            nickname = request.nickname,
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