package io.github.leoframework.utils.buffers.resizable;

import io.github.leoframework.utils.buffers.access.DirectBufferAccessBase;

import static io.github.leoframework.utils.unsafe.UnsafeAccess.UNSAFE;

public interface ResizableDirectBufferBase extends ResizableBufferBase, DirectBufferAccessBase {
    default long reallocate(final long elements) {
        return UNSAFE.reallocateMemory(getDataBuffer(), getIndexScale() * elements);
    }
}
