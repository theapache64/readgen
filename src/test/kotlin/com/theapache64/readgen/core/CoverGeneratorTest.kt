package com.theapache64.readgen.core

import com.theapache64.expekt.should
import org.junit.Test
import java.io.File

class CoverGeneratorTest {
    @Test
    fun generateCover() {
        val output = CoverGenerator.generate("THIS IS A TEST")
        output.exists().should.`true`
    }

    @Test
    fun draw() {
        val output = CoverGenerator.draw(
            File("src/test/resources/cover_without_text.jpeg"),
            "Damn It!"
        )
        output.exists().should.`true`
    }
}