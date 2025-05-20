package com.group11.bookstore.services

import com.group11.bookstore.models.User
import com.group11.bookstore.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService
@Autowired constructor(val userRepository: UserRepository) {

    val passwordEncoder: PasswordEncoder =
        Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8()

    fun checkLogin(user: User): Boolean {

        val userInDatabase = userRepository.findByUsername(user.username)
        if (userInDatabase == null) {
            return false
        }

        val isPasswordMatches = passwordEncoder.matches(
            user.password,
            userInDatabase.password
        )

        if (!isPasswordMatches) {
            return false
        }

        user.id = userInDatabase.id
        user.role = userInDatabase.role
        user.email = userInDatabase.email
        user.fullName = userInDatabase.fullName
        user.phoneNumber = userInDatabase.phoneNumber

        return true
    }
}