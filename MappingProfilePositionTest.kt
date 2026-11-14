package com.mt7.keymapper.core.mapping

import org.junit.Assert.assertEquals
import org.junit.Test

class MappingProfilePositionTest {
    @Test
    fun defaultControlPositionsAreNormalized() {
        val profile = MappingProfile(id = "test", name = "Teste")
        listOf(
            profile.joystickPositionX, profile.joystickPositionY,
            profile.viewPositionX, profile.viewPositionY,
            profile.firePositionX, profile.firePositionY
        ).forEach { value ->
            assertEquals(true, value in 0.0f..1.0f)
        }
    }

    @Test(expected = IllegalArgumentException::class)
    fun positionOutsideScreenIsRejected() {
        MappingProfile(id = "test", name = "Teste", firePositionX = 1.1f)
    }
}
