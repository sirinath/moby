package com.sirinath.utils.buffers.api.ints;

import com.sirinath.utils.buffers.api.common.IndexPosition;

public interface IntNavigableBufferBase extends IndexPosition {
    int get();

    void put(final int value);

    int getVolatile();

    void putVolatile(final int x);

    void putOrdered(final int x);

    int getAndAdd(final int delta);

    int getAndSet(final int newValue);

    void compareAndSwap(final int expected, final int x);
}
