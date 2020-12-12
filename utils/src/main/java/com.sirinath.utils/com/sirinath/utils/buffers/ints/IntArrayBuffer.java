package com.sirinath.utils.buffers.ints;

import com.sirinath.utils.buffers.api.ints.IntArrayBufferBase;

public class IntArrayBuffer implements IntArrayBufferBase<IntArrayBuffer> {
    private final int[] array;
    private final long length;

    public IntArrayBuffer(final int length) {
        this.length = length;
        this.array = new int[length];
    }

    @Override
    public int[] getArray() {
        return new int[0];
    }

    @Override
    public long length() {
        return length;
    }
}
