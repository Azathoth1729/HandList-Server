package com.azathoth.handlistserver.model.user

import jakarta.persistence.*
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity(name = "_user")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long = 0,
    private val username: String,
    private val email: String,
    private val password: String,

    @Enumerated(EnumType.STRING)
    private val role: UserRole,
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