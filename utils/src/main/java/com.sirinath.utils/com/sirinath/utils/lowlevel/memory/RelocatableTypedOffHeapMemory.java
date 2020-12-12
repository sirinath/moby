package com.sirinath.utils.lowlevel.memory;

import com.sirinath.utils.lowlevel.Fields;

import java.lang.reflect.Field;

public final class RelocatableTypedOffHeapMemory<T> extends TypedOffHeapMemory<T> {
    public RelocatableTypedOffHeapMemory(final long bytes) {
        super(bytes);
    }

    private volatile long updating = 0;
    private static final long updatingOffset = Fields.Offset.$(RelocatableTypedOffHeapMemory.class, "updating");

    public final void reallocate(final long bytes) {
        try {
            if (Memory.Longs.getAndAdd(this, updatingOffset, 1) > 0) do Thread.onSpinWait(); while (updating > 1);

            final long address = getAddress();
            Memory.acquireFence();

            setAddress(Memory.reallocate(address, bytes));
            Memory.releaseFence();
        } finally {
            Memory.Longs.getAndAdd(this, updatingOffset, -1);
        }
    }
}
