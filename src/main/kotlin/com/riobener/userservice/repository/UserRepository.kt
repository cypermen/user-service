package com.riobener.userservice.repository

import com.riobener.userservice.entity.JpaUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*


@Repository
interface UserRepository : JpaRepository<JpaUser?, UUID?> {
    fun findById(id: UUID?): JpaUser?
}