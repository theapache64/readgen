package com.theapache64.readgen.core

import com.theapache64.expekt.should
import com.theapache64.readgen.model.Config
import com.theapache64.readgen.model.ProjectType
import org.junit.Test
import java.io.File

class ReadMeManagerTest {

    @Test
    fun getGenerated() {
        val actualOutput = ReadMeManager.getGenerated(
            Config(
                "JohnDoe",
                "myGitHubUsername",
                "myTwitterUsername",
                "my@email.com",
                "myPatronUsername",
                "myBMCUsername"
            ),
            File("/home/theapache64/Documents/projects/readgen"),
            ProjectType.JAR,
            "A simple tool to generate README"
        )

        actualOutput.should.not.contain("{{")
    }
}