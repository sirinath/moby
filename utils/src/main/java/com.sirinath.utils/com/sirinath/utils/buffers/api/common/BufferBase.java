package com.sirinath.utils.buffers.api.common;

import com.sirinath.utils.buffers.api.accessors.BufferAccessorBase;

public interface BufferBase<S extends BufferBase<S, T>, T> extends BufferAccessorBase<S, T> {
    long getOffset();

    default long getIndexStart() {
        return getOffset();
    }

    long getElementSize();

    long length();

    private static long translateIndex(final long startIndex, final long scale, final long index) {
        return startIndex + scale * index;
    }

    default long translateIndex(final long index) {
        return translateIndex(getIndexStart(), getElementSize(), index);
    }

    default long sizeInBytes() {
        return length() * getElementSize();
    }
}
