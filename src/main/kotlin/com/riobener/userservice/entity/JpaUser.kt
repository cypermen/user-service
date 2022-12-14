package com.riobener.userservice.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.*

@Entity
data class JpaUser(
    @Id
    val id: UUID,
    val name: String,
    val departmentId: UUID,
)