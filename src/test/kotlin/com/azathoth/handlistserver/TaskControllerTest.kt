package com.azathoth.handlistserver

import com.azathoth.handlistserver.security.WebSecurityConfig
import com.azathoth.handlistserver.task.TaskController
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.Import
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@Import(WebSecurityConfig::class)
@WebMvcTest(controllers = [TaskController::class])
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
class TaskControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun get() {
        mockMvc.get("/tasks").andExpect {
            status { isOk() }
            content {
                contentTypeCompatibleWith("application/json")

            }
        }
    }

}