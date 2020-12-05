package io.github.leoframework.utils.buffers.common;

import io.github.leoframework.utils.buffers.access.DirectBufferAccessBase;

public interface DirectBufferBase extends BufferBase, DirectBufferAccessBase {
    default long sizeInBytes(final long elements) {
        return elements * getIndexScale();
    }
}
