package com.sirinath.utils.buffers.api.ints;

public interface IntBufferBase {
    int get(final long index);

    void put(final long index, final int value);

    int getVolatile(final long index);

    void putVolatile(final long index, final int x);

    void putOrdered(final long index, final int x);

    int getAndAdd(final long index, final int delta);

    int getAndSet(final long index, final int newValue);

    boolean compareAndSwap(final long index, final int expected, final int x);
}
