package com.mt7.keymapper.core.runtime

import com.mt7.keymapper.core.activation.BackendKind
import com.mt7.keymapper.core.activation.BackendStatus
import com.mt7.keymapper.core.mapping.FireMode
import com.mt7.keymapper.core.mapping.JoystickSettings
import com.mt7.keymapper.core.mapping.JoystickVector
import com.mt7.keymapper.core.mapping.PointerDelta
import com.mt7.keymapper.core.mapping.VisionSettings
import org.junit.Assert.*
import org.junit.Test

class LocalMappingSessionTest {
    private val authorized = BackendStatus(BackendKind.ANDROID, true, "Local test backend")

    @Test fun eventsAreBlockedUntilSessionIsReady() {
        val session = LocalMappingSession()
        assertNull(session.processPointer(PointerDelta(2f, 3f), VisionSettings()))
        assertNull(session.processJoystick(JoystickVector(0.5f, 0.5f), JoystickSettings()))
        session.start(BackendStatus(BackendKind.ANDROID, false, "Not authorized"))
        assertNull(session.processPointer(PointerDelta(2f, 3f), VisionSettings()))
    }

    @Test fun pointerAndJoystickAreProcessedAfterLocalStart() {
        val session = LocalMappingSession()
        assertEquals(RuntimeState.READY, session.start(authorized).state)
        assertNotNull(session.processPointer(PointerDelta(2f, 3f), VisionSettings()))
        assertNotNull(session.processJoystick(JoystickVector(0.5f, 0.5f), JoystickSettings()))
    }

    @Test fun pauseAndStopBlockEventsAndResetFireState() {
        val session = LocalMappingSession()
        session.start(authorized)
        session.setFireMode(FireMode.HOLD)
        assertTrue(session.activateFire().isPressed)
        assertEquals(RuntimeState.PAUSED, session.pause().state)
        assertFalse(session.fireSnapshot().isPressed)
        assertNull(session.processJoystick(JoystickVector(1f, 0f), JoystickSettings()))
        session.resume()
        session.activateFire()
        assertEquals(RuntimeState.STOPPED, session.stop().state)
        assertFalse(session.fireSnapshot().isPressed)
        assertNull(session.processPointer(PointerDelta(1f, 1f), VisionSettings()))
    }

    @Test fun fireModeCanOnlyActivateWhenReady() {
        val session = LocalMappingSession()
        assertFalse(session.activateFire().isPressed)
        session.start(authorized)
        session.setFireMode(FireMode.TOGGLE)
        assertTrue(session.activateFire().isPressed)
        assertFalse(session.activateFire().isPressed)
    }
}
