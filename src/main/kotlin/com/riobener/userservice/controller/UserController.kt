package com.riobener.userservice.controller

import com.riobener.userservice.entity.JpaUser
import com.riobener.userservice.service.UserService
import io.opentracing.Span
import io.opentracing.Tracer
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/users")
class UserController(
    private val userService: UserService,
) {
    @GetMapping("/health")
    fun checkHealth() = "All good"

    @PostMapping("/")
    fun saveUser(@RequestParam name: String, @RequestParam departmentId: String): JpaUser {
        return userService.saveUser(
            JpaUser(
                id = UUID.randomUUID(),
                name = name,
                departmentId = UUID.fromString(departmentId)
            )
        )
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable("id") userId: String): JpaUser? {
        return userService.getUser(UUID.fromString(userId))
    }
}