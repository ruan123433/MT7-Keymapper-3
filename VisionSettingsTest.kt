package com.mt7.keymapper.core.mapping

import org.junit.Assert.assertEquals
import org.junit.Test

class VisionSettingsTest {
    @Test fun supportsFractionalAxisSensitivity() {
        val settings = VisionSettings(sensitivityX = 0.1f, sensitivityY = 0.2f)
        assertEquals(0.1f, settings.sensitivityX, 0.0001f)
        assertEquals(0.2f, settings.sensitivityY, 0.0001f)
    }

    @Test fun defaultsTo250Hz() {
        assertEquals(250, VisionSettings().pollingRateHz)
    }
}
