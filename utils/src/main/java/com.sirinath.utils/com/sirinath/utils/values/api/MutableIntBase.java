package com.sirinath.utils.values.api;

import java.lang.reflect.Field;

public interface MutableIntBase {
    long getFieldOffset();

    Field getField();

    Object getObject();

    int get();

    void put(final int value);

    int getVolatile();

    void putVolatile(final int x);

    int getAndAdd(final int delta);

    int getAndSet(final int newValue);

    void compareAndSwap(final int expected, final int x);
}
