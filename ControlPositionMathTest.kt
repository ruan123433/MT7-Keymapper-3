package com.mt7.keymapper.core.mapping

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class ControlPositionMathTest {
    private val markers = listOf(
        ControlMarker("Joystick", 20f, 80f),
        ControlMarker("Visão", 70f, 50f),
        ControlMarker("Atirar", 90f, 80f)
    )

    @Test fun selectsNearestMarkerInsideHitRadius() {
        assertEquals("Visão", ControlPositionMath.nearestMarker(75f, 52f, markers))
    }

    @Test fun rejectsTouchesOutsideHitRadius() {
        assertNull(ControlPositionMath.nearestMarker(0f, 0f, markers))
    }

    @Test fun clampsNormalizedCoordinates() {
        assertEquals(0f, ControlPositionMath.normalize(-10f, 100), 0f)
        assertEquals(1f, ControlPositionMath.normalize(120f, 100), 0f)
        assertEquals(0.5f, ControlPositionMath.normalize(50f, 100), 0f)
    }

    @Test fun handlesZeroSizedBounds() {
        assertEquals(1f, ControlPositionMath.normalize(12f, 0), 0f)
    }
}
