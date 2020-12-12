package com.sirinath.utils.buffers.ints;

import com.sirinath.utils.buffers.api.ints.IntDirectBufferBase;
import com.sirinath.utils.lowlevel.Fields;
import com.sirinath.utils.lowlevel.memory.Memory;
import com.sirinath.utils.lowlevel.memory.TypedOffHeapMemory;

import java.lang.reflect.Field;

public class IntDirectBuffer implements IntDirectBufferBase<IntDirectBuffer> {
    private long memoryAddress;
    private final long length;
    private final TypedOffHeapMemory<int[]> address;

    public IntDirectBuffer(final long elements) {
        length = elements;
        address = new TypedOffHeapMemory<>(sizeInBytes());
        memoryAddress = address.getAddress();
    }

    @Override
    public long getMemoryAddress() {
        return memoryAddress;
    }

    @Override
    public TypedOffHeapMemory<int[]> getAddress() {
        return address;
    }

    @Override
    public long length() {
        return length;
    }

    private volatile long updating = 0;
    private static final long updatingOffset = Fields.Offset.$(IntDirectBuffer.class, "updating");

    @Override
    public void close() throws Exception {
        if (Memory.Longs.getAndAdd(this, updatingOffset, 1) == 0) {
            address.close();
            memoryAddress = 0;
            Memory.releaseFence();
        }
    }
}
