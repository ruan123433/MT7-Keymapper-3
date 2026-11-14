package com.mt7.keymapper.core.mapping

import org.junit.Assert.assertEquals
import org.junit.Test

class PointerTransformTest {
    @Test
    fun appliesIndependentAxisSensitivity() {
        val settings = VisionSettings(
            sensitivityX = 2f,
            sensitivityY = 0.5f
        )
        val result = PointerTransform().apply(PointerDelta(10f, 10f), settings)
        assertEquals(20f, result.x, 0.001f)
        assertEquals(5f, result.y, 0.001f)
    }

    @Test
    fun decimalSensitivityIsPreserved() {
        val settings = VisionSettings(
            sensitivityX = 0.1f,
            sensitivityY = 0.2f
        )
        val result = PointerTransform().apply(PointerDelta(10f, 5f), settings)
        assertEquals(1f, result.x, 0.001f)
        assertEquals(1f, result.y, 0.001f)
    }

    @Test
    fun sameDistanceHasSameGainRegardlessOfInputSpeed() {
        val transform = PointerTransform()
        val settings = VisionSettings(
            sensitivityX = 0.75f,
            sensitivityY = 0.75f,
            accelerationProtection = true
        )
        val slow = transform.apply(PointerDelta(2f, 0f), settings)
        val fast = transform.apply(PointerDelta(20f, 0f), settings)
        assertEquals(10f, fast.x / slow.x, 0.001f)
    }
}
