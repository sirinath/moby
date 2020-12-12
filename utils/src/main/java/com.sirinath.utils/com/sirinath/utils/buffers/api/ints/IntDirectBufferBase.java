package com.sirinath.utils.buffers.api.ints;

import com.sirinath.utils.buffers.api.accessors.DirectBufferAccessorBase;
import com.sirinath.utils.buffers.api.common.BufferBase;
import com.sirinath.utils.lowlevel.memory.Memory;

public interface IntDirectBufferBase<S extends IntDirectBufferBase<S>> extends IntBufferBase, BufferBase<S, int[]>, DirectBufferAccessorBase<S, int[]>, AutoCloseable {
    long BUFFER_BASE_OFFSET = 0;
    long BUFFER_INDEX_SCALE = Memory.Ints.ARRAY_INDEX_SCALE;

    @Override
    default long getOffset() {
        return BUFFER_BASE_OFFSET;
    }

    @Override
    default long getElementSize() {
        return BUFFER_INDEX_SCALE;
    }

    @Override
    default int get(final long index) {
        return Memory.Ints.get(getDataBuffer(), translateIndex(index));
    }

    @Override
    default void put(final long index, final int value) {
        Memory.Ints.put(getDataBuffer(), translateIndex(index), value);
    }

    @Override
    default int getVolatile(final long index) {
        return Memory.Ints.getVolatile(getDataBuffer(), translateIndex(index));
    }

    @Override
    default void putVolatile(final long index, final int value) {
        Memory.Ints.putVolatile(getDataBuffer(), translateIndex(index), value);
    }

    @Override
    default int getAndAdd(final long index, final int delta) {
        return Memory.Ints.getAndAdd(getDataBuffer(), translateIndex(index), delta);
    }

    @Override
    default int getAndSet(final long index, final int newValue) {
        return Memory.Ints.getAndSet(getDataBuffer(), translateIndex(index), newValue);
    }

    @Override
    default boolean compareAndSwap(final long index, final int expected, final int x) {
        return Memory.Ints.compareAndSwap(getDataBuffer(), translateIndex(index), expected, x);
    }
}
