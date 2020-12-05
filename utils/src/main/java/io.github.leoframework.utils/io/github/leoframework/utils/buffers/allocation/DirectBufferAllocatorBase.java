package io.github.leoframework.utils.buffers.allocation;

import static io.github.leoframework.utils.unsafe.UnsafeAccess.UNSAFE;

public interface DirectBufferAllocatorBase extends BufferAllocatorBase {
    default long allocate(final long elements) {
        return UNSAFE.allocateMemory(getIndexScale() * elements);
    }
}
