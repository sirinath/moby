package io.github.leoframework.utils.buffers.forint;

public interface IntBufferBase {
    int get(final long index);

    void put(final long index, final int value);
}
