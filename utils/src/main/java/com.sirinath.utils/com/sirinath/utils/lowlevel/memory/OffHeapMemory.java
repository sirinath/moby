package com.sirinath.utils.lowlevel.memory;

import com.sirinath.utils.lowlevel.Fields;

import java.lang.ref.Cleaner;
import java.lang.reflect.Field;

public class OffHeapMemory implements AutoCloseable {
    private static final Cleaner cleaner = Cleaner.create();

    private final Cleaner.Cleanable cleanable;

    private long address;

    private final RunCleaner runCleaner;

    private final static class RunCleaner implements Runnable {
        private long address;

        RunCleaner(final long address) {
            this.address = address;
        }

        @Override
        public void run() {
            Memory.free(address);
            address = 0;
        }

        public final void setAddress(final long address) {
            this.address = address;
        }
    }

    public OffHeapMemory(final long bytes) {
        address = Memory.allocate(Memory.aligned(bytes));
        runCleaner = new RunCleaner(address);
        cleanable = cleaner.register(this, runCleaner);
    }

    private volatile long updating = 0;
    private static final long updatingOffset = Fields.Offset.$(OffHeapMemory.class, "updating");

    @Override
    public final void close() throws Exception {
        if (Memory.Longs.getAndAdd(this, updatingOffset, 1) == 0) {
            cleanable.clean();
            address = 0;
            Memory.releaseFence();
        }
    }

    public final long getAddress() {
        return address;
    }

    protected final void setAddress(final long address) {
        this.address = address;
        runCleaner.setAddress(address);
        Memory.releaseFence();
    }
}
