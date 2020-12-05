package io.github.leoframework.utils.buffers.resizable;

import io.github.leoframework.utils.buffers.access.ArrayBufferAccessBase;

public interface ResizableArrayBufferBase<T> extends ResizableBufferBase, ArrayBufferAccessBase<T> {
    T reallocate(final int elements);
}
