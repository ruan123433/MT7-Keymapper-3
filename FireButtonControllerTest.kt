package com.mt7.keymapper.core.mapping

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class FireButtonControllerTest {
    @Test
    fun tapEmitsOnePressAndReleasePair() {
        val controller = FireButtonController(FireMode.TAP)

        val result = controller.activate()

        assertFalse(result.isPressed)
        assertEquals(2L, result.transitionCount)
        assertEquals(FireTransition.RELEASED, result.lastTransition)
    }

    @Test
    fun holdStaysPressedUntilExplicitRelease() {
        val controller = FireButtonController(FireMode.HOLD)

        val pressed = controller.activate()
        val repeatedPress = controller.activate()
        val released = controller.release()

        assertTrue(pressed.isPressed)
        assertTrue(repeatedPress.isPressed)
        assertEquals(1L, repeatedPress.transitionCount)
        assertFalse(released.isPressed)
        assertEquals(2L, released.transitionCount)
    }

    @Test
    fun toggleChangesOnlyWhenActivated() {
        val controller = FireButtonController(FireMode.TOGGLE)

        val first = controller.activate()
        val ignoredRelease = controller.release()
        val second = controller.activate()

        assertTrue(first.isPressed)
        assertTrue(ignoredRelease.isPressed)
        assertFalse(second.isPressed)
        assertEquals(2L, second.transitionCount)
    }

    @Test
    fun changingModeReleasesAnActiveHold() {
        val controller = FireButtonController(FireMode.HOLD)
        controller.activate()

        val changed = controller.setMode(FireMode.TAP)

        assertEquals(FireMode.TAP, changed.mode)
        assertFalse(changed.isPressed)
        assertEquals(2L, changed.transitionCount)
    }
}
