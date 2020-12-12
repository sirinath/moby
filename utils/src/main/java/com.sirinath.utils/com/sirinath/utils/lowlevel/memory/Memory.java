package com.sirinath.utils.lowlevel.memory;

import com.sirinath.utils.lowlevel.Exceptions;
import com.sirinath.utils.lowlevel.UnsafeAccess;
import sun.misc.Unsafe;

public final class Memory {
    private Memory() {}

    public static final long ALIGN_PATTERS = Long.BYTES - 1;

    private static final Unsafe $ = UnsafeAccess.$;

    public static long allocate(final long bytes) {
        return $.allocateMemory(bytes);
    }

    public static <T> Object allocate(final Class<T> c) {
        try {
            return $.allocateInstance(c);
        } catch (Throwable t) {
            Exceptions.Unchecked.$(t);
        }

        return null;
    }

    public static void free(final long address) {
        $.freeMemory(address);
    }

    public static long reallocate(final long address, final long bytes) {
        return $.reallocateMemory(address, bytes);
    }

    public static void fullFence() {
        $.fullFence();
    }

    public static void acquireFence() {
        $.loadFence();
    }

    public static void releaseFence() {
        $.storeFence();
    }

    public static long aligned(final long bytes, final long pattern) {
        return (((bytes - 1) | pattern) + 1);
    }

    public static long aligned(final long bytes) {
        return Memory.aligned(bytes, ALIGN_PATTERS);
    }

    public static final class Booleans {
        private Booleans() {}

        public static final long ALIGN_PATTERS = UnsafeAccess.$.ARRAY_BOOLEAN_INDEX_SCALE - 1;

        public static long ARRAY_BASE_OFFSET = UnsafeAccess.$.ARRAY_BOOLEAN_BASE_OFFSET;
        public static long ARRAY_INDEX_SCALE = UnsafeAccess.$.ARRAY_BOOLEAN_INDEX_SCALE;

        public static long aligned(final long bytes) {
            return Memory.aligned(bytes, ALIGN_PATTERS);
        }

        public static boolean $(final Object o, final long offset) {
            return $.getBoolean(o, offset);
        }

        public static void $(final Object o, final long offset, final boolean x) {
            $.putBoolean(o, offset, x);
        }

        public static boolean get(final Object o, final long offset) {
            return $.getBoolean(o, offset);
        }

        public static void put(final Object o, final long offset, final boolean x) {
            $.putBoolean(o, offset, x);
        }

        public static boolean getVolatile(final Object o, final long offset) {
            return $.getBooleanVolatile(o, offset);
        }

        public static void putVolatile(final Object o, final long offset, final boolean x) {
            $.putBooleanVolatile(o, offset, x);
        }

        public static boolean $(final long address, final long offset) {
            return $.getBoolean(null,address + offset);
        }

        public static void $(final long address, final long offset, final boolean x) {
            $.putBoolean(null, address + offset, x);
        }

        public static boolean get(final long address, final long offset) {
            return $.getBoolean(null, address + offset);
        }

        public static void put(final long address, final long offset, final boolean x) {
            $.putBoolean(null, address + offset, x);
        }

        public static boolean getVolatile(final long address, final long offset) {
            return $.getBooleanVolatile(null, address + offset);
        }

        public static void putVolatile(final long address, final long offset, final boolean x) {
            $.putBooleanVolatile(null, address + offset, x);
        }
    }

    public static final class Ints {
        private Ints() {}

        public static final long ALIGN_PATTERS = Integer.BYTES - 1;

        public static long ARRAY_BASE_OFFSET = UnsafeAccess.$.ARRAY_INT_BASE_OFFSET;
        public static long ARRAY_INDEX_SCALE = UnsafeAccess.$.ARRAY_INT_INDEX_SCALE;

        public static long aligned(final long bytes) {
            return Memory.aligned(bytes, ALIGN_PATTERS);
        }

        public static int $(final Object o, final long offset) {
            return $.getInt(o, offset);
        }

        public static void $(final Object o, final long offset, final int x) {
            $.putInt(o, offset, x);
        }

        public static int get(final Object o, final long offset) {
            return $.getInt(o, offset);
        }

        public static void put(final Object o, final long offset, final int x) {
            $.putInt(o, offset, x);
        }

        public static int getVolatile(final Object o, final long offset) {
            return $.getIntVolatile(o, offset);
        }

        public static void putVolatile(final Object o, final long offset, final int x) {
            $.putIntVolatile(o, offset, x);
        }

        public static int getAndAdd(final Object o, final long offset, final int delta) {
            return $.getAndAddInt(o, offset, delta);
        }

        public static int getAndSet(final Object o, final long offset, final int newValue) {
            return $.getAndSetInt(o, offset, newValue);
        }

        public static boolean compareAndSwap(final Object o, final long offset, final int expected, final int x) {
            return $.compareAndSwapInt(o, offset, expected, x);
        }

        public static int $(final long address, final long offset) {
            return $.getInt(address + offset);
        }

        public static void $(final long address, final long offset, final int x) {
            $.putInt(address + offset, x);
        }

        public static int get(final long address, final long offset) {
            return $.getInt(address + offset);
        }

        public static void put(final long address, final long offset, final int x) {
            $.putInt(address + offset, x);
        }

        public static int getVolatile(final long address, final long offset) {
            return $.getIntVolatile(null, address + offset);
        }

        public static void putVolatile(final long address, final long offset, final int x) {
            $.putIntVolatile(null, address + offset, x);
        }

        public static int getAndAdd(final long address, final long offset, final int delta) {
            return $.getAndAddInt(null, address + offset, delta);
        }

        public static int getAndSet(final long address, final long offset, final int newValue) {
            return $.getAndSetInt(null, address + offset, newValue);
        }

        public static boolean compareAndSwap(final long address, final long offset, final int expected, final int x) {
            return $.compareAndSwapInt(null, address + offset, expected, x);
        }
    }

    public static final class Longs {
        private Longs() {}

        public static final long ALIGN_PATTERS = Long.BYTES - 1;

        public static long ARRAY_BASE_OFFSET = UnsafeAccess.$.ARRAY_LONG_BASE_OFFSET;
        public static long ARRAY_INDEX_SCALE = UnsafeAccess.$.ARRAY_LONG_INDEX_SCALE;

        public static long aligned(final long bytes) {
            return Memory.aligned(bytes, ALIGN_PATTERS);
        }

        public static long $(final Object o, final long offset) {
            return $.getLong(o, offset);
        }

        public static void $(final Object o, final long offset, final long x) {
            $.putLong(o, offset, x);
        }

        public static long get(final Object o, final long offset) {
            return $.getLong(o, offset);
        }

        public static void put(final Object o, final long offset, final long x) {
            $.putLong(o, offset, x);
        }

        public static long getVolatile(final Object o, final long offset) {
            return $.getLongVolatile(o, offset);
        }

        public static void putVolatile(final Object o, final long offset, final long x) {
            $.putLongVolatile(o, offset, x);
        }

        public static long getAndAdd(final Object o, final long offset, final long delta) {
            return $.getAndAddLong(o, offset, delta);
        }

        public static long getAndSet(final Object o, final long offset, final long newValue) {
            return $.getAndSetLong(o, offset, newValue);
        }

        public static boolean compareAndSwap(final Object o, final long offset, final long expected, final long x) {
            return $.compareAndSwapLong(o, offset, expected, x);
        }

        public static long $(final long address, final long offset) {
            return $.getLong(address + offset);
        }

        public static void $(final long address, final long offset, final long x) {
            $.putLong(address + offset, x);
        }

        public static long get(final long address, final long offset) {
            return $.getLong(address + offset);
        }

        public static void put(final long address, final long offset, final long x) {
            $.putLong(address + offset, x);
        }

        public static long getVolatile(final long address, final long offset) {
            return $.getLongVolatile(null, address + offset);
        }

        public static void putVolatile(final long address, final long offset, final long x) {
            $.putLongVolatile(null, address + offset, x);
        }

        public static long getAndAdd(final long address, final long offset, final long delta) {
            return $.getAndAddLong(null, address + offset, delta);
        }

        public static long getAndSet(final long address, final long offset, final long newValue) {
            return $.getAndSetLong(null, address + offset, newValue);
        }

        public static boolean compareAndSwap(final long address, final long offset, final long expected, final long x) {
            return $.compareAndSwapLong(null, address + offset, expected, x);
        }
    }
}
