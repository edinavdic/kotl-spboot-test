package com.edo.nb.ktsb.controller

import com.edo.nb.ktsb.model.Bank
import com.fasterxml.jackson.databind.ObjectMapper
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.*

@SpringBootTest
@AutoConfigureMockMvc
internal class BankControllerTest(@Autowired val objectMapper: ObjectMapper){

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun `should return all banks`(){
        //when/then
        mockMvc.get("/api/banks")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$[0].accountNumber") {value("1234")}
                }

    }

    @Test
    fun `should return the bank with the given account number`(){
        //when/then
        val accountNumber = 1234
        mockMvc.get("/api/banks/$accountNumber")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.trust") {value("3.14")}
                }

    }

    @Test
    fun `should return NOT FOUND if the account number does not exist`(){
        //when/then
        val accountNumber = "does_not_exists"
        mockMvc.get("/api/banks/$accountNumber")
                .andDo { print() }
                .andExpect {
                    status { isNotFound() }
                }

    }

    @Test
    fun `should add the new bank`(){
        //given/when/then
        val newBank = Bank("acc123", 3.21, 12)
        mockMvc.post("/api/banks"){
                    contentType = MediaType.APPLICATION_JSON
                    content = objectMapper.writeValueAsString(newBank)

                }
                .andDo { print() }
                .andExpect {
                    status { isCreated() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.accountNumber") {value("acc123")}
                }

    }

    @Test
    fun `should return BAD REQUEST if bank with given accountNumber already exists`(){
        //given/when/then
        val newBank = Bank("1234", 3.14, 17)
        mockMvc.post("/api/banks"){
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(newBank)

        }
                .andDo { print() }
                .andExpect {
                    status { isBadRequest() }

                    }

    }

    @Test
    fun `should update existing bank`(){
        //given/when/then
        val updatedBank = Bank("1234", 1.14, 127)
        mockMvc.patch("/api/banks"){
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(updatedBank)

        }
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.trust") {value(updatedBank.trust)}
                }

        mockMvc.get("/api/banks/${updatedBank.accountNumber}")
                .andExpect { content { json(objectMapper.writeValueAsString(updatedBank)) } }
    }

    @Test
    fun `should return NOT FOUND if bank with given accountNumber does not exist`(){
        //given/when/then
        val updatedBank = Bank("12s34", 1.124, 1327)
        mockMvc.patch("/api/banks"){
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(updatedBank)

        }
                .andDo { print() }
                .andExpect {
                    status { isNotFound() }

                }

    }

    @Test
    fun `should delete existing bank with given account number`(){
        //given/when/then
        val deleteAccountNumber = "122234"
        mockMvc.delete("/api/banks/$deleteAccountNumber")
                .andDo { print() }
                .andExpect {
                    status { isNoContent() }
                }

        mockMvc.get("/api/banks/$deleteAccountNumber")
                .andExpect { status { isNotFound() } }
    }

    @Test
    fun `should return NOT FOUND if bank with given accountNumber does not exist for DELETE`(){
        //given/when/then
        val deleteAccountNumber = "12ssss34"
        mockMvc.delete("/api/banks/$deleteAccountNumber")
                .andDo { print() }
                .andExpect {
                    status { isNotFound() }
                }
    }
}