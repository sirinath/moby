package com.sirinath.utils.buffers;

import com.sirinath.utils.UnsafeAccess;
import sun.misc.Unsafe;

public abstract class AbstractDirectBuffer implements Buffer {
   private static final Unsafe UNSAFE = UnsafeAccess.UNSAFE;

   public abstract long getAddress();

   public abstract long getIndexScale();

   @Override
   public final boolean compareAndSwapInt(final long index, final int expected, final int x) {
      return UNSAFE.compareAndSwapInt(null,
                                      Buffer.calculateIndex(getAddress(), index, Unsafe.ARRAY_INT_INDEX_SCALE),
                                      expected,
                                      x);
   }

   @Override
   public final boolean compareAndSwapLong(final long index, final long expected, final long x) {
      return UNSAFE.compareAndSwapLong(null,
                                       Buffer.calculateIndex(getAddress(), index, Unsafe.ARRAY_LONG_INDEX_SCALE),
                                       expected,
                                       x);
   }

   @Override
   public final boolean compareAndSwapObject(final long index, final Object expected, final Object x) {
      return UNSAFE.compareAndSwapObject(null,
                                         Buffer.calculateIndex(getAddress(), index, Unsafe.ARRAY_OBJECT_INDEX_SCALE),
                                         expected,
                                         x);
   }

   @Override
   public final int getAndAddInt(final long index, final int x) {
      return UNSAFE.getAndAddInt(null, Buffer.calculateIndex(getAddress(), index, Unsafe.ARRAY_INT_INDEX_SCALE), x);
   }

   @Override
   public final long getAndAddLong(final long index, final long x) {
      return UNSAFE.getAndAddLong(null, Buffer.calculateIndex(getAddress(), index, Unsafe.ARRAY_LONG_INDEX_SCALE), x);
   }

   @Override
   public final int getAndSetInt(final long index, final int x) {
      return UNSAFE.getAndSetInt(null, Buffer.calculateIndex(getAddress(), index, Unsafe.ARRAY_INT_INDEX_SCALE), x);
   }

   @Override
   public final long getAndSetLong(final long index, final long x) {
      return UNSAFE.getAndSetLong(null, Buffer.calculateIndex(getAddress(), index, Unsafe.ARRAY_LONG_INDEX_SCALE), x);
   }

   @Override
   public final Object getAndSetObject(final long index, final Object x) {
      return UNSAFE.getAndSetObject(null,
                                    Buffer.calculateIndex(getAddress(), index, Unsafe.ARRAY_OBJECT_INDEX_SCALE),
                                    x);
   }

   @Override
   public final boolean getBoolean(final long index) {
      return UNSAFE.getBoolean(null, Buffer.calculateIndex(getAddress(), index, Unsafe.ARRAY_BOOLEAN_INDEX_SCALE));
   }

   @Override
   public final boolean getBooleanVolatile(final long index) {
      return UNSAFE.getBooleanVolatile(null,
                                       Buffer.calculateIndex(getAddress(), index, Unsafe.ARRAY_BOOLEAN_INDEX_SCALE));
   }

   @Override
   public final byte getByte(final long index) {
      return UNSAFE.getByte(Buffer.calculateIndex(getAddress(), index, Unsafe.ARRAY_BYTE_INDEX_SCALE));
   }

   @Override
   public final byte getByteVolatile(final long index) {
      return UNSAFE.getByteVolatile(null, Buffer.calculateIndex(getAddress(), index, Unsafe.ARRAY_BYTE_INDEX_SCALE));
   }

   @Override
   public final char getChar(final long index) {
      return UNSAFE.getChar(Buffer.calculateIndex(getAddress(), index, Unsafe.ARRAY_CHAR_INDEX_SCALE));
   }

   @Override
   public final char getCharVolatile(final long index) {
      return UNSAFE.getCharVolatile(null, Buffer.calculateIndex(getAddress(), index, Unsafe.ARRAY_CHAR_INDEX_SCALE));
   }

   @Override
   public final double getDouble(final long index) {
      return UNSAFE.getDouble(Buffer.calculateIndex(getAddress(), index, Unsafe.ARRAY_DOUBLE_INDEX_SCALE));
   }

   @Override
   public final double getDoubleVolatile(final long index) {
      return UNSAFE.getDoubleVolatile(null,
                                      Buffer.calculateIndex(getAddress(), index, Unsafe.ARRAY_DOUBLE_INDEX_SCALE));
   }

   @Override
   public final float getFloat(final long index) {
      return UNSAFE.getFloat(Buffer.calculateIndex(getAddress(), index, Unsafe.ARRAY_FLOAT_INDEX_SCALE));
   }

   @Override
   public final float getFloatVolatile(final long index) {
      return UNSAFE.getFloatVolatile(null, Buffer.calculateIndex(getAddress(), index, Unsafe.ARRAY_FLOAT_INDEX_SCALE));
   }

   @Override
   public final int getInt(final long index) {
      return UNSAFE.getInt(Buffer.calculateIndex(getAddress(), index, Unsafe.ARRAY_INT_INDEX_SCALE));
   }

   @Override
   public final int getIntVolatile(final long index) {
      return UNSAFE.getIntVolatile(null, Buffer.calculateIndex(getAddress(), index, Unsafe.ARRAY_INT_INDEX_SCALE));
   }

   @Override
   public final long getLong(final long index) {
      return UNSAFE.getLong(Buffer.calculateIndex(getAddress(), index, Unsafe.ARRAY_LONG_INDEX_SCALE));
   }

   @Override
   public final long getLongVolatile(final long index) {
      return UNSAFE.getLongVolatile(null, Buffer.calculateIndex(getAddress(), index, Unsafe.ARRAY_LONG_INDEX_SCALE));
   }

   @Override
   public final Object getObject(final long index) {
      return UNSAFE.getObject(null, Buffer.calculateIndex(getAddress(), index, Unsafe.ARRAY_OBJECT_INDEX_SCALE));
   }

   @Override
   public final Object getObjectVolatile(final long index) {
      return UNSAFE.getObjectVolatile(null,
                                      Buffer.calculateIndex(getAddress(), index, Unsafe.ARRAY_OBJECT_INDEX_SCALE));
   }

   @Override
   public final short getShort(final long index) {
      return UNSAFE.getShort(Buffer.calculateIndex(getAddress(), index, Unsafe.ARRAY_SHORT_INDEX_SCALE));
   }

   @Override
   public final short getShortVolatile(final long index) {
      return UNSAFE.getShortVolatile(null, Buffer.calculateIndex(getAddress(), index, Unsafe.ARRAY_SHORT_INDEX_SCALE));
   }

   @Override
   public final void putBoolean(final long index, final boolean x) {
      UNSAFE.putBoolean(null, Buffer.calculateIndex(getAddress(), index, Unsafe.ARRAY_BOOLEAN_INDEX_SCALE), x);
   }

   @Override
   public final void putBooleanVolatile(final long index, final boolean x) {
      UNSAFE.putBooleanVolatile(null, Buffer.calculateIndex(getAddress(), index, Unsafe.ARRAY_BOOLEAN_INDEX_SCALE), x);
   }

   @Override
   public final void putByte(final long index, final byte x) {
      UNSAFE.putByte(Buffer.calculateIndex(getAddress(), index, Unsafe.ARRAY_BYTE_INDEX_SCALE), x);
   }

   @Override
   public final void putByteVolatile(final long index, final byte x) {
      UNSAFE.putByteVolatile(null, Buffer.calculateIndex(getAddress(), index, Unsafe.ARRAY_BYTE_INDEX_SCALE), x);
   }

   @Override
   public final void putChar(final long index, final char x) {
      UNSAFE.putChar(Buffer.calculateIndex(getAddress(), index, Unsafe.ARRAY_CHAR_INDEX_SCALE), x);
   }

   @Override
   public final void putCharVolatile(final long index, final char x) {
      UNSAFE.putCharVolatile(null, Buffer.calculateIndex(getAddress(), index, Unsafe.ARRAY_CHAR_INDEX_SCALE), x);
   }

   @Override
   public final void putDouble(final long index, final double x) {
      UNSAFE.putDouble(Buffer.calculateIndex(getAddress(), index, Unsafe.ARRAY_DOUBLE_INDEX_SCALE), x);
   }

   @Override
   public final void putDoubleVolatile(final long index, final double x) {
      UNSAFE.putDoubleVolatile(null, Buffer.calculateIndex(getAddress(), index, Unsafe.ARRAY_DOUBLE_INDEX_SCALE), x);
   }

   @Override
   public final void putFloat(final long index, final float x) {
      UNSAFE.putFloat(Buffer.calculateIndex(getAddress(), index, Unsafe.ARRAY_FLOAT_INDEX_SCALE), x);
   }

   @Override
   public final void putFloatVolatile(final long index, final float x) {
      UNSAFE.putFloatVolatile(null, Buffer.calculateIndex(getAddress(), index, Unsafe.ARRAY_FLOAT_INDEX_SCALE), x);
   }

   @Override
   public final void putInt(final long index, final int x) {
      UNSAFE.putInt(Buffer.calculateIndex(getAddress(), index, Unsafe.ARRAY_INT_INDEX_SCALE), x);
   }

   @Override
   public final void putIntVolatile(final long index, final int x) {
      UNSAFE.putIntVolatile(null, Buffer.calculateIndex(getAddress(), index, Unsafe.ARRAY_INT_INDEX_SCALE), x);
   }

   @Override
   public final void putLong(final long index, final long x) {
      UNSAFE.putLong(Buffer.calculateIndex(getAddress(), index, Unsafe.ARRAY_LONG_INDEX_SCALE), x);
   }

   @Override
   public final void putLongVolatile(final long index, final long x) {
      UNSAFE.putLongVolatile(null, Buffer.calculateIndex(getAddress(), index, Unsafe.ARRAY_LONG_INDEX_SCALE), x);
   }

   @Override
   public final void putObject(final long index, final Object x) {
      UNSAFE.putObject(null, Buffer.calculateIndex(getAddress(), index, Unsafe.ARRAY_OBJECT_INDEX_SCALE), x);
   }

   @Override
   public final void putObjectVolatile(final long index, final Object x) {
      UNSAFE.putObjectVolatile(null, Buffer.calculateIndex(getAddress(), index, Unsafe.ARRAY_OBJECT_INDEX_SCALE), x);
   }

   @Override
   public final void putShort(final long index, final short x) {
      UNSAFE.putShort(Buffer.calculateIndex(getAddress(), index, Unsafe.ARRAY_SHORT_INDEX_SCALE), x);
   }

   @Override
   public final void putShortVolatile(final long index, final short x) {
      UNSAFE.putShortVolatile(null, Buffer.calculateIndex(getAddress(), index, Unsafe.ARRAY_SHORT_INDEX_SCALE), x);
   }

   @Override
   public final void putOrderedInt(final long index, final int x) {
      UNSAFE.putOrderedInt(null, Buffer.calculateIndex(getAddress(), index, Unsafe.ARRAY_INT_INDEX_SCALE), x);
   }

   @Override
   public final void putOrderedLong(final long index, final long x) {
      UNSAFE.putOrderedLong(null, Buffer.calculateIndex(getAddress(), index, Unsafe.ARRAY_LONG_INDEX_SCALE), x);
   }

   @Override
   public final void putOrderedObject(final long index, final Object x) {
      UNSAFE.putOrderedObject(null, Buffer.calculateIndex(getAddress(), index, Unsafe.ARRAY_OBJECT_INDEX_SCALE), x);
   }

   @Override
   public final void copy(final long srcOffset, final AbstractDirectBuffer destBuffer, final long destOffset,
                          final long elements) {
      Buffer.copy(Buffer.calculateIndex(getAddress(), srcOffset, getIndexScale()),
                  Buffer.calculateIndex(destBuffer.getAddress(), destOffset, getIndexScale()),
                  elements * getIndexScale());
   }

   @Override
   public final void copy(final long srcOffset, final AbstractArrayBuffer destBuffer, final long destOffset,
                          final long elements) {
      Buffer.copy(null,
                  Buffer.calculateIndex(getAddress(), srcOffset, getIndexScale()),
                  destBuffer.getArray(),
                  Buffer.calculateIndex(destBuffer.getBaseOffset(), destOffset, destBuffer.getIndexScale()),
                  elements * getIndexScale());
   }

   public final long getAddress(final long index) {
      return UNSAFE.getAddress(Buffer.calculateIndex(getAddress(), index, Unsafe.ARRAY_LONG_INDEX_SCALE));
   }

   public final void putAddress(final long index, final long x) {
      UNSAFE.putAddress(Buffer.calculateIndex(getAddress(), index, Unsafe.ARRAY_LONG_INDEX_SCALE), x);
   }
}