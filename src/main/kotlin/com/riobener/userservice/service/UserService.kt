package com.riobener.userservice.service

import com.riobener.userservice.entity.JpaUser
import com.riobener.userservice.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(
    private val userRepository: UserRepository,
) {

    fun saveUser(jpaUser: JpaUser): JpaUser {
        return userRepository.save(jpaUser)
    }

    fun getUser(userId: UUID?): JpaUser? {
        return userRepository.findById(userId)
    }
}