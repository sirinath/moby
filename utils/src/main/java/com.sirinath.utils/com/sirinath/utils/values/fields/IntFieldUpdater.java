package com.sirinath.utils.values.fields;

import com.sirinath.utils.values.api.MutableIntBase;
import com.sirinath.utils.lowlevel.Fields;
import com.sirinath.utils.lowlevel.memory.Memory;

import java.lang.reflect.Field;

public final class IntFieldUpdater implements MutableIntBase {
    private final Object object;
    private final Field field;
    private final long valueOffset;

    public <T> IntFieldUpdater(final T object, final String field) {
        this.object = object;
        this.field = Fields.$(MutableInt.class, field);
        this.valueOffset = Fields.Offset.$(this.field);
    }

    public static <T> IntFieldUpdater $(final T object, final String field) {
        return new IntFieldUpdater(object, field);
    }

    @Override
    public final long getFieldOffset() {
        return valueOffset;
    }

    @Override
    public Object getObject() {
        return object;
    }

    @Override
    public Field getField() {
        return field;
    }

    @Override
    public final int get() {
        return Memory.Ints.get(object, getFieldOffset());
    }

    @Override
    public final void put(final int value) {
        Memory.Ints.put(object, getFieldOffset(), value);
    }

    @Override
    public final int getVolatile() {
        return Memory.Ints.getVolatile(object, getFieldOffset());
    }

    @Override
    public final void putVolatile(final int value) {
        Memory.Ints.putVolatile(object, getFieldOffset(), value);
    }

    @Override
    public final int getAndAdd(final int delta) {
        return Memory.Ints.getAndAdd(object, getFieldOffset(), delta);
    }

    @Override
    public final int getAndSet(final int newValue) {
        return Memory.Ints.getAndSet(object, getFieldOffset(), newValue);
    }

    @Override
    public final void compareAndSwap(final int expected, final int x) {
        Memory.Ints.compareAndSwap(object, getFieldOffset(), expected, x);
    }
}
