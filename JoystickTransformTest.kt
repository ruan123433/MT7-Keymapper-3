package com.mt7.keymapper.core.mapping

import org.junit.Assert.assertEquals
import org.junit.Test

class JoystickTransformTest {
    private val transform = JoystickTransform()

    @Test fun radialDeadzoneReturnsZero() {
        val out = transform.apply(JoystickVector(0.02f, 0.02f), JoystickSettings(deadzone = 0.1f))
        assertEquals(0f, out.x, 0.0001f)
        assertEquals(0f, out.y, 0.0001f)
    }

    @Test fun clampsOutputToNormalizedRange() {
        val out = transform.apply(JoystickVector(1f, 1f), JoystickSettings(2f, 2f, 0f))
        assertEquals(1f, out.x, 0.0001f)
        assertEquals(1f, out.y, 0.0001f)
    }

    @Test fun respectsIndependentAxisSensitivity() {
        val out = transform.apply(JoystickVector(0.5f, 0.5f), JoystickSettings(2f, 0.5f, 0f))
        assertEquals(1f, out.x, 0.0001f)
        assertEquals(0.25f, out.y, 0.0001f)
    }
}
