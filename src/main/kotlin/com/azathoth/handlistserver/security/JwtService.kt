package com.azathoth.handlistserver.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*
import javax.crypto.SecretKey

@Service
class JwtService {
    companion object {
        private const val SECRET_KEY = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970"
    }

    fun extractUsername(token: String) =
        extractClaim(token, Claims::getSubject)

    fun extractExpiration(token: String) =
        extractClaim(token, Claims::getExpiration)

    fun isTokenValid(token: String, userDetails: UserDetails): Boolean =
        extractUsername(token) == userDetails.username && !isTokenExpired(token)

    fun isTokenExpired(token: String): Boolean =
        extractExpiration(token).before(Date())

    fun generateToken(userDetails: UserDetails): String =
        generateToken(HashMap(), userDetails)

    fun generateToken(extraClaims: Map<String, Any>, userDetails: UserDetails): String =
        Jwts.builder()
            .setClaims(extraClaims)
            .setSubject(userDetails.username)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
            .compact()


    fun <T> extractClaim(token: String, claimsResolver: (Claims) -> T): T =
        claimsResolver(extractAllClaims(token))

    private fun extractAllClaims(token: String): Claims =
        Jwts.parserBuilder()
            .setSigningKey(getSignInKey())
            .build()
            .parseClaimsJws(token)
            .body

    fun getSignInKey(): SecretKey =
        Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY))


}