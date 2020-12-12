package com.sirinath.utils.lowlevel.memory;

public class TypedOffHeapMemory<T> extends OffHeapMemory {
    public TypedOffHeapMemory(final long bytes) {
        super(bytes);
    }
}
