import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.lang.ref.WeakReference;

import org.junit.jupiter.api.Test;

class GarbageCollectorTest {

    @Test
    void gc() {
        WeakReference<Object> weak = new WeakReference<>(new Object());
        assertNotNull(weak.get());

        System.gc();
        assertNull(weak.get());
    }
}
