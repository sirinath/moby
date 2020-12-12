package com.sirinath.utils.buffers.api.ints;

import com.sirinath.utils.buffers.api.accessors.DirectBufferAccessorBase;
import com.sirinath.utils.buffers.api.common.BufferBase;
import com.sirinath.utils.lowlevel.memory.Memory;

public interface IntNavigableDirectBufferBase<S extends IntNavigableDirectBufferBase<S>> extends IntNavigableBufferBase, BufferBase<S, int[]>, DirectBufferAccessorBase<S, int[]> {
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
    default int get() {
        return Memory.Ints.get(getDataBuffer(), translateIndex(getIndexPosition()));
    }

    @Override
    default void put(final int value) {
        Memory.Ints.put(getDataBuffer(), translateIndex(getIndexPosition()), value);
    }

    @Override
    default int getVolatile() {
        return Memory.Ints.getVolatile(getDataBuffer(), translateIndex(getIndexPosition()));
    }

    @Override
    default void putVolatile(final int value) {
        Memory.Ints.putVolatile(getDataBuffer(), translateIndex(getIndexPosition()), value);
    }

    @Override
    default int getAndAdd(final int delta) {
        return Memory.Ints.getAndAdd(getDataBuffer(), translateIndex(getIndexPosition()), delta);
    }

    @Override
    default int getAndSet(final int newValue) {
        return Memory.Ints.getAndSet(getDataBuffer(), translateIndex(getIndexPosition()), newValue);
    }

    @Override
    default void compareAndSwap(final int expected, final int x) {
        Memory.Ints.compareAndSwap(getDataBuffer(), translateIndex(getIndexPosition()), expected, x);
    }
}
