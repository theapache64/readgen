package com.theapache64.readgen.core

import com.theapache64.expekt.should
import org.junit.Test
import java.io.File

class CoverGeneratorTest {
    @Test
    fun generateCover() {
        val output = CoverGenerator.generate("READGEN", 100, null)
        output.exists().should.`true`
    }

    @Test
    fun draw() {
        val output = CoverGenerator.draw(
            inputFile = File("src/test/resources/cover_without_text.jpeg"),
            text = "Damn It!",
            _fontSize = 100,
            customFont = null,
            isFromTest = true
        )
        output.exists().should.`true`
    }
}