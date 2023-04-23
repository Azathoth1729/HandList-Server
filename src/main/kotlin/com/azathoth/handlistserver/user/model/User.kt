package com.azathoth.handlistserver.user.model

import jakarta.persistence.*
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity(name = "_user")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long = 0,
    var nickname: String,
    var email: String,
    private val password: String,

    @Enumerated(EnumType.STRING)
    var role: UserRole,
) : UserDetails {
    override fun getAuthorities() =
        listOf(SimpleGrantedAuthority(role.toString()))

    override fun getPassword(): String = password
    override fun getUsername(): String = email
    override fun isAccountNonExpired(): Boolean = true
    override fun isAccountNonLocked(): Boolean = true
    override fun isCredentialsNonExpired(): Boolean = true
    override fun isEnabled(): Boolean = true
}