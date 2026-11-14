package com.mt7.keymapper.core.runtime

import com.mt7.keymapper.core.activation.BackendKind
import com.mt7.keymapper.core.activation.BackendStatus
import com.mt7.keymapper.core.mapping.PointerDelta
import com.mt7.keymapper.core.mapping.VisionSettings
import org.junit.Assert.*
import org.junit.Test

class MappingRuntimeTest {
    private val authorized = BackendStatus(BackendKind.SHIZUKU, true, "Authorized")
    private val denied = BackendStatus(BackendKind.ROOT, false, "Not authorized")
    private val settings = VisionSettings(
        sensitivityX = 1.5f,
        sensitivityY = 0.5f,
        accelerationProtection = true,
        pollingRateHz = 250
    )

    @Test fun requiresAuthorizedBackendBeforeProcessing() {
        val runtime = MappingRuntime()
        runtime.selectBackend(denied)
        runtime.start()
        assertEquals(RuntimeState.WAITING_AUTHORIZATION, runtime.snapshot().state)
        assertNull(runtime.process(PointerDelta(2f, 4f), settings))
    }

    @Test fun processesLocalDeltaWhenReady() {
        val runtime = MappingRuntime()
        runtime.selectBackend(authorized)
        runtime.start()
        assertEquals(RuntimeState.READY, runtime.snapshot().state)
        assertEquals(PointerDelta(3f, 2f), runtime.process(PointerDelta(2f, 4f), settings))
    }

    @Test fun pauseAndStopBlockProcessing() {
        val runtime = MappingRuntime()
        runtime.selectBackend(authorized)
        runtime.start()
        runtime.pause()
        assertEquals(RuntimeState.PAUSED, runtime.snapshot().state)
        assertNull(runtime.process(PointerDelta(1f, 1f), settings))
        runtime.stop()
        assertEquals(RuntimeState.STOPPED, runtime.snapshot().state)
        assertNull(runtime.process(PointerDelta(1f, 1f), settings))
    }

    @Test fun backendAuthorizationCanBeRefreshed() {
        val runtime = MappingRuntime()
        runtime.selectBackend(denied)
        runtime.start()
        runtime.selectBackend(authorized)
        assertEquals(RuntimeState.READY, runtime.snapshot().state)
    }
}
