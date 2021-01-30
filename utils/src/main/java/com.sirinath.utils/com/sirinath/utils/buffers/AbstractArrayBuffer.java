package com.sirinath.utils.buffers;

import com.sirinath.utils.UnsafeAccess;
import sun.misc.Unsafe;

public abstract class AbstractArrayBuffer implements Buffer {
   private static final Unsafe UNSAFE = UnsafeAccess.UNSAFE;

   public abstract Object getArray();

   public abstract long getBaseOffset();

   public abstract long getIndexScale();

   @Override
   public final void copy(final long srcOffset, final AbstractArrayBuffer destBuffer, final long destOffset,
                          final long elements) {
      Buffer.copy(getArray(), Buffer.calculateIndex(getBaseOffset(), srcOffset, getIndexScale()),
                  destBuffer.getArray(), Buffer.calculateIndex(destBuffer.getBaseOffset(), destOffset,
                                                               destBuffer.getIndexScale()),
                                                    elements * getIndexScale());
   }

   @Override
   public final void copy(final long srcOffset, final AbstractDirectBuffer destBuffer, final long destOffset,
                          final long elements) {
      Buffer.copy(getArray(), Buffer.calculateIndex(getBaseOffset(), srcOffset, getIndexScale()),
                  null, Buffer.calculateIndex(destBuffer.getAddress(), destOffset,
                                                               destBuffer.getIndexScale()),
                  elements * getIndexScale());
   }

   @Override
   public final boolean compareAndSwapInt(final long index, final int expected, final int x) {
      return UNSAFE.compareAndSwapInt(getArray(),
                                      Buffer.calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_INT_INDEX_SCALE),
                                      expected,
                                      x);
   }

   @Override
   public final boolean compareAndSwapLong(final long index, final long expected, final long x) {
      return UNSAFE.compareAndSwapLong(getArray(),
                                       Buffer.calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_LONG_INDEX_SCALE),
                                       expected,
                                       x);
   }

   @Override
   public final boolean compareAndSwapObject(final long index, final Object expected, final Object x) {
      return UNSAFE.compareAndSwapObject(getArray(),
                                         Buffer.calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_OBJECT_INDEX_SCALE),
                                         expected,
                                         x);
   }

   @Override
   public final int getAndAddInt(final long index, final int x) {
      return UNSAFE.getAndAddInt(getArray(),
                                 Buffer.calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_INT_INDEX_SCALE),
                                 x);
   }

   @Override
   public final long getAndAddLong(final long index, final long x) {
      return UNSAFE.getAndAddLong(getArray(),
                                  Buffer.calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_LONG_INDEX_SCALE),
                                  x);
   }

   @Override
   public final int getAndSetInt(final long index, final int x) {
      return UNSAFE.getAndSetInt(getArray(),
                                 Buffer.calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_INT_INDEX_SCALE),
                                 x);
   }

   @Override
   public final long getAndSetLong(final long index, final long x) {
      return UNSAFE.getAndSetLong(getArray(),
                                  Buffer.calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_LONG_INDEX_SCALE),
                                  x);
   }

   @Override
   public final Object getAndSetObject(final long index, final Object x) {
      return UNSAFE.getAndSetObject(getArray(),
                                    Buffer.calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_OBJECT_INDEX_SCALE),
                                    x);
   }

   @Override
   public final boolean getBoolean(final long index) {
      return UNSAFE.getBoolean(getArray(),
                               Buffer.calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_BOOLEAN_INDEX_SCALE));
   }

   @Override
   public final boolean getBooleanVolatile(final long index) {
      return UNSAFE.getBooleanVolatile(getArray(),
                                       Buffer.calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_BOOLEAN_INDEX_SCALE));
   }

   @Override
   public final byte getByte(final long index) {
      return UNSAFE.getByte(getArray(), Buffer.calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_BYTE_INDEX_SCALE));
   }

   @Override
   public final byte getByteVolatile(final long index) {
      return UNSAFE.getByteVolatile(getArray(),
                                    Buffer.calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_BYTE_INDEX_SCALE));
   }

   @Override
   public final char getChar(final long index) {
      return UNSAFE.getChar(getArray(), Buffer.calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_CHAR_INDEX_SCALE));
   }

   @Override
   public final char getCharVolatile(final long index) {
      return UNSAFE.getCharVolatile(getArray(),
                                    Buffer.calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_CHAR_INDEX_SCALE));
   }

   @Override
   public final double getDouble(final long index) {
      return UNSAFE.getDouble(getArray(),
                              Buffer.calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_DOUBLE_INDEX_SCALE));
   }

   @Override
   public final double getDoubleVolatile(final long index) {
      return UNSAFE.getDoubleVolatile(getArray(),
                                      Buffer.calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_DOUBLE_INDEX_SCALE));
   }

   @Override
   public final float getFloat(final long index) {
      return UNSAFE.getFloat(getArray(), Buffer.calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_FLOAT_INDEX_SCALE));
   }

   @Override
   public final float getFloatVolatile(final long index) {
      return UNSAFE.getFloatVolatile(getArray(),
                                     Buffer.calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_FLOAT_INDEX_SCALE));
   }

   @Override
   public final int getInt(final long index) {
      return UNSAFE.getInt(getArray(), Buffer.calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_INT_INDEX_SCALE));
   }

   @Override
   public final int getIntVolatile(final long index) {
      return UNSAFE.getIntVolatile(getArray(),
                                   Buffer.calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_INT_INDEX_SCALE));
   }

   @Override
   public final long getLong(final long index) {
      return UNSAFE.getLong(getArray(), Buffer.calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_LONG_INDEX_SCALE));
   }

   @Override
   public final long getLongVolatile(final long index) {
      return UNSAFE.getLongVolatile(getArray(),
                                    Buffer.calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_LONG_INDEX_SCALE));
   }

   @Override
   public final Object getObject(final long index) {
      return UNSAFE.getObject(getArray(),
                              Buffer.calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_OBJECT_INDEX_SCALE));
   }

   @Override
   public final Object getObjectVolatile(final long index) {
      return UNSAFE.getObjectVolatile(getArray(),
                                      Buffer.calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_OBJECT_INDEX_SCALE));
   }

   @Override
   public final short getShort(final long index) {
      return UNSAFE.getShort(getArray(), Buffer.calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_SHORT_INDEX_SCALE));
   }

   @Override
   public final short getShortVolatile(final long index) {
      return UNSAFE.getShortVolatile(getArray(),
                                     Buffer.calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_SHORT_INDEX_SCALE));
   }

   @Override
   public final void putBoolean(final long index, final boolean x) {
      UNSAFE.putBoolean(getArray(), Buffer.calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_BOOLEAN_INDEX_SCALE), x);
   }

   @Override
   public final void putBooleanVolatile(final long index, final boolean x) {
      UNSAFE.putBooleanVolatile(getArray(),
                                Buffer.calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_BOOLEAN_INDEX_SCALE),
                                x);
   }

   @Override
   public final void putByte(final long index, final byte x) {
      UNSAFE.putByte(getArray(), Buffer.calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_BYTE_INDEX_SCALE), x);
   }

   @Override
   public final void putByteVolatile(final long index, final byte x) {
      UNSAFE.putByteVolatile(getArray(),
                             Buffer.calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_BYTE_INDEX_SCALE),
                             x);
   }

   @Override
   public final void putChar(final long index, final char x) {
      UNSAFE.putChar(getArray(), Buffer.calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_CHAR_INDEX_SCALE), x);
   }

   @Override
   public final void putCharVolatile(final long index, final char x) {
      UNSAFE.putCharVolatile(getArray(),
                             Buffer.calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_CHAR_INDEX_SCALE),
                             x);
   }

   @Override
   public final void putDouble(final long index, final double x) {
      UNSAFE.putDouble(getArray(), Buffer.calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_DOUBLE_INDEX_SCALE), x);
   }

   @Override
   public final void putDoubleVolatile(final long index, final double x) {
      UNSAFE.putDoubleVolatile(getArray(),
                               Buffer.calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_DOUBLE_INDEX_SCALE),
                               x);
   }

   @Override
   public final void putFloat(final long index, final float x) {
      UNSAFE.putFloat(getArray(), Buffer.calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_FLOAT_INDEX_SCALE), x);
   }

   @Override
   public final void putFloatVolatile(final long index, final float x) {
      UNSAFE.putFloatVolatile(getArray(),
                              Buffer.calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_FLOAT_INDEX_SCALE),
                              x);
   }

   @Override
   public final void putInt(final long index, final int x) {
      UNSAFE.putInt(getArray(), Buffer.calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_INT_INDEX_SCALE), x);
   }

   @Override
   public final void putIntVolatile(final long index, final int x) {
      UNSAFE.putIntVolatile(getArray(), Buffer.calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_INT_INDEX_SCALE), x);
   }

   @Override
   public final void putLong(final long index, final long x) {
      UNSAFE.putLong(getArray(), Buffer.calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_LONG_INDEX_SCALE), x);
   }

   @Override
   public final void putLongVolatile(final long index, final long x) {
      UNSAFE.putLongVolatile(getArray(),
                             Buffer.calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_LONG_INDEX_SCALE),
                             x);
   }

   @Override
   public final void putObject(final long index, final Object x) {
      UNSAFE.putObject(getArray(), Buffer.calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_OBJECT_INDEX_SCALE), x);
   }

   @Override
   public final void putObjectVolatile(final long index, final Object x) {
      UNSAFE.putObjectVolatile(getArray(),
                               Buffer.calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_OBJECT_INDEX_SCALE),
                               x);
   }

   @Override
   public final void putShort(final long index, final short x) {
      UNSAFE.putShort(getArray(), Buffer.calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_SHORT_INDEX_SCALE), x);
   }

   @Override
   public final void putShortVolatile(final long index, final short x) {
      UNSAFE.putShortVolatile(getArray(),
                              Buffer.calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_SHORT_INDEX_SCALE),
                              x);
   }

   @Override
   public final void putOrderedInt(final long index, final int x) {
      UNSAFE.putOrderedInt(getArray(), Buffer.calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_INT_INDEX_SCALE), x);
   }

   @Override
   public final void putOrderedLong(final long index, final long x) {
      UNSAFE.putOrderedLong(getArray(),
                            Buffer.calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_LONG_INDEX_SCALE),
                            x);
   }

   @Override
   public final void putOrderedObject(final long index, final Object x) {
      UNSAFE.putOrderedObject(getArray(),
                              Buffer.calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_OBJECT_INDEX_SCALE),
                              x);
   }
}
