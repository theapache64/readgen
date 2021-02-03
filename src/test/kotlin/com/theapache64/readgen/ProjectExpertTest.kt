package com.theapache64.readgen

import com.theapache64.expekt.should
import org.junit.Test
import java.io.File

class ProjectExpertTest {

    @Test
    fun `Given Java gradle project, then JAR`() {
        ProjectExpert.getProjectType(
            File("/home/theapache64/Documents/projects/auto-motion")
        ).should.equal(ProjectType.JAR)
    }

    @Test
    fun `Given Android project, then Android`() {
        ProjectExpert.getProjectType(
            File("/home/theapache64/Documents/projects/topcorn")
        ).should.equal(ProjectType.ANDROID)
    }

    @Test
    fun `Given non gradle project, then exception`() {
        try {
            ProjectExpert.getProjectType(
                File("/home/theapache64/Documents/projects/theapache64")
            )
            assert(false)
        } catch (e: IllegalStateException) {
            assert(true)
        }
    }

    @Test
    fun `Given family JAR project, then FamilyJar`() {
        ProjectExpert.getJarType(
            File("/home/theapache64/Documents/projects/readgen")
        ).should.equal(JarType.FAMILY_JAR)
    }


    @Test
    fun `Given single JAR project, then SingleJar`() {
        ProjectExpert.getJarType(
            File("/home/theapache64/Documents/projects/organizer")
        ).should.equal(JarType.SINGLE_JAR)
    }

    @Test
    fun `Given invalid project, then exception`() {
        try {
            ProjectExpert.getJarType(
                File("/home/theapache64/Documents/projects/theapache64")
            )
            assert(false)
        } catch (e: IllegalStateException) {
            assert(true)
        }
    }
}