package com.theapache64.readgen.core

import com.theapache64.expekt.should
import com.theapache64.readgen.model.Config
import org.junit.Test
import java.io.File

class InstallScriptManagerTest {

    @Test
    fun getGenerated() {
        val actualOutput = InstallScriptManager.getGenerated(
            Config(
                "JohnDoe",
                "myGitHubUsername",
                "myTwitterUsername",
                "my@email.com",
                "myPatronUsername",
                "myBMCUsername",
                "myPayPalUsername"
            ),
            File("/home/theapache64/Documents/projects/readgen")
        )

        actualOutput.should.not.contain("{{")
    }
}