package com.riobener.userservice

import com.ninjasquad.springmockk.MockkBean
import com.riobener.userservice.entity.JpaUser
import com.riobener.userservice.service.UserService
import io.mockk.every
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.util.*


@WebMvcTest
class EndPointTest(@Autowired val mockMvc: MockMvc) {
    @MockkBean
    lateinit var userService: UserService
    val id = UUID.randomUUID()
    val user = JpaUser(id = id, name = "Benjamin", UUID.randomUUID())
    @Test
    fun givenExistingUser_whenGetRequest_thenReturnsUserJsonWithStatus200() {
        every { userService.getUser(id) } returns user;
        mockMvc.perform(get("/users/$id"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.name").value("Benjamin"))
    }
    @Test
    fun givenUserDoesntExist_whenGetRequest_thenReturnsEmptyBody() {
        val randomId = UUID.randomUUID()
        every { userService.getUser(randomId) } returns null

        mockMvc.perform(get("/users/$randomId"))
            .andExpect(jsonPath("$").doesNotExist())
    }
    @Test
    fun whenPostRequestWithUserData_thenReturnsStatus200() {
        val name = "Ben"
        val departmentId = UUID.randomUUID()
        val content = "?name=${name}&departmentId=${departmentId}"
        val newUser = JpaUser(
            id = UUID.randomUUID(), name, departmentId = departmentId
        )
        every { userService.saveUser(any())}.returns(newUser)
        mockMvc.perform(post("/users/$content").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.name").value("Ben"))
        verify{userService.saveUser(any())}
    }
}