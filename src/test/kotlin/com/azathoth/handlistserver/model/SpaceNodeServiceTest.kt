package com.azathoth.handlistserver.model

import com.azathoth.handlistserver.model.spacenode.SpaceNode
import com.azathoth.handlistserver.model.spacenode.SpaceNodeService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest

@WebMvcTest
class SpaceNodeServiceTest() {
    @MockkBean
    lateinit var nodeRepo: SpaceNodeService

    @Test
    fun addNodeTest() {
    }

    @Test
    fun removeNodeTest() {
    }
}