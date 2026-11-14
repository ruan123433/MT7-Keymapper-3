package com.mt7.keymapper.core.runtime

import com.mt7.keymapper.core.mapping.VisionSettings
import org.junit.Assert.*
import org.junit.Test

class LocalTestEventSourceTest {
    @Test fun createsExplicitLocalDelta() {
        val delta = LocalTestEventSource().createDelta(2f, -3f)
        assertEquals(2f, delta.x, 0f)
        assertEquals(-3f, delta.y, 0f)
    }

    @Test fun runtimeBlocksEventsUntilStartedAndAuthorized() {
        val runtime = MappingRuntime()
        assertNull(runtime.process(LocalTestEventSource().createDelta(1f, 1f), VisionSettings()))
    }
}
