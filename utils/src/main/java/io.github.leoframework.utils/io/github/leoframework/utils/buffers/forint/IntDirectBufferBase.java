package io.github.leoframework.utils.buffers.forint;

import io.github.leoframework.utils.buffers.common.DirectBufferBase;

import static io.github.leoframework.utils.unsafe.UnsafeAccess.UNSAFE;

public interface IntDirectBufferBase extends IntBufferBase, DirectBufferBase {
    long INDEX_SCALE = UNSAFE.ARRAY_INT_INDEX_SCALE;

    @Override
    default long getIndexStart() {
        return getDataBuffer();
    }

    @Override
    default long getIndexScale() {
        return INDEX_SCALE;
    }

    @Override
    default int get(final long index) {
        return UNSAFE.getInt(translateIndex(index));
    }

    @Override
    default void put(final long index, final int value) {
        UNSAFE.putInt(translateIndex(index), value);
    }
}
