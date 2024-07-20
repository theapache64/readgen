package com.theapache64.readgen.core

import com.theapache64.expekt.should
import com.theapache64.readgen.model.Config
import com.theapache64.readgen.model.ProjectType
import org.junit.Test

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
                "myBMCUsername",
                "myPayPalUsername",
                "myUnsplashApiKey"
            ),
            "readgen",
            ProjectType.JAR,
            "A simple tool to generate README"
        )

        actualOutput.should.not.contain("{{")
    }
}