package com.azathoth.handlistserver.model.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserService(@Autowired val userRepo: UserRepository) : UserDetailsService {
    override fun loadUserByUsername(email: String): UserDetails =
        userRepo.findByEmail(email) ?: throw UsernameNotFoundException("$email not found")

}