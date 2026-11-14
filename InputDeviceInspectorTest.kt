package com.mt7.keymapper.core.devices

import org.junit.Assert.assertEquals
import org.junit.Test

class InputDeviceInspectorTest {
    @Test
    fun classifiesMouse() {
        val labels = InputDeviceInspector.sourceLabels(
            keyboard = false, mouse = true, gamepad = false, joystick = false,
            dpad = false, touchscreen = false, stylus = false, trackball = false
        )
        assertEquals(listOf("Mouse"), labels)
        assertEquals("Mouse", InputDeviceInspector.classify(labels))
    }

    @Test
    fun retainsMultipleSources() {
        val labels = InputDeviceInspector.sourceLabels(
            keyboard = true, mouse = true, gamepad = false, joystick = false,
            dpad = false, touchscreen = false, stylus = false, trackball = false
        )
        assertEquals(listOf("Teclado", "Mouse"), labels)
        assertEquals("Mouse + teclas/controle", InputDeviceInspector.classify(labels))
    }

    @Test
    fun labelsUnknownSources() {
        val labels = InputDeviceInspector.sourceLabels(
            keyboard = false, mouse = false, gamepad = false, joystick = false,
            dpad = false, touchscreen = false, stylus = false, trackball = false
        )
        assertEquals(listOf("Fonte não identificada"), labels)
    }
}
