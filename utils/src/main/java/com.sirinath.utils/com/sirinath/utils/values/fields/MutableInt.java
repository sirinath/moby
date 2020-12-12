package com.sirinath.utils.values.fields;

import com.sirinath.utils.values.api.MutableIntBase;
import com.sirinath.utils.lowlevel.Fields;
import com.sirinath.utils.lowlevel.memory.Memory;

import java.lang.reflect.Field;

public class MutableInt implements MutableIntBase {
    private int value;
    private final static Field field = Fields.$(MutableInt.class, "value");
    private final static long valueOffset = Fields.Offset.$(field);

    public MutableInt() {
        this(0);
    }

    public MutableInt(final int value) {
        this.value = value;
    }

    @Override
    public long getFieldOffset() {
        return valueOffset;
    }

    @Override
    public Field getField() {
        return field;
    }

    @Override
    public Object getObject() {
        return this;
    }

    @Override
    public int get() {
        return value;
    }

    @Override
    public void put(final int value) {
        this.value = value;
    }

    @Override
    public int getVolatile() {
        return Memory.Ints.getVolatile(this, getFieldOffset());
    }

    @Override
    public void putVolatile(final int value) {
        Memory.Ints.putVolatile(this, getFieldOffset(), value);
    }

    @Override
    public int getAndAdd(final int delta) {
        return Memory.Ints.getAndAdd(this, getFieldOffset(), delta);
    }

    @Override
    public int getAndSet(final int newValue) {
        return Memory.Ints.getAndSet(this, getFieldOffset(), newValue);
    }

    @Override
    public void compareAndSwap(final int expected, final int x) {
        Memory.Ints.compareAndSwap(this, getFieldOffset(), expected, x);
    }
}
