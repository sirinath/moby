package io.github.leoframework.utils.buffers.forint;

import io.github.leoframework.utils.buffers.common.ArrayBufferBase;

import static io.github.leoframework.utils.unsafe.UnsafeAccess.UNSAFE;

public interface IntArrayBufferBase extends IntBufferBase, ArrayBufferBase<int[]> {
    long ARRAY_BASE_OFFSET = UNSAFE.ARRAY_INT_BASE_OFFSET;
    long ARRAY_INDEX_SCALE = UNSAFE.ARRAY_INT_INDEX_SCALE;

    @Override
    default long getIndexStart() {
        return ARRAY_BASE_OFFSET;
    }

    @Override
    default long getIndexScale() {
        return ARRAY_INDEX_SCALE;
    }

    @Override
    default int get(final long index) {
        return UNSAFE.getInt(getDataBuffer(), translateIndex(index));
    }

    @Override
    default void put(final long index, final int value) {
        UNSAFE.putInt(getDataBuffer(), translateIndex(index), value);
    }

    default int getVolatile(final long index) {
        return UNSAFE.getIntVolatile(getDataBuffer(), translateIndex(index));
    }

    default void putVolatile(final long index, final int value) {
        UNSAFE.putIntVolatile(getDataBuffer(), translateIndex(index), value);
    }
}
