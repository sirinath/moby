package com.sirinath.utils.buffers;

import com.sirinath.utils.UnsafeAccess;
import sun.misc.Unsafe;

public final class ArrayBufferWithPosition extends AbstractArrayBuffer implements BufferPosition {
   private static final Unsafe UNSAFE = UnsafeAccess.UNSAFE;
   private static final long positionOffset = initPositionOffset();
   private final Object array;
   private final long   baseOffset;
   private final long   indexScale;
   private final long   sizeInBytes;
   private final long   size;
   private volatile     long position;

   public <T> ArrayBufferWithPosition(final T[] array, final long position) {
      this((Object) array, Unsafe.ARRAY_OBJECT_BASE_OFFSET, Unsafe.ARRAY_OBJECT_INDEX_SCALE, array.length, position);
   }

   private ArrayBufferWithPosition(final Object array, final long baseOffset, final long indexScale, final long size,
                                   final long position) {
      this.array = array;
      this.baseOffset = baseOffset;
      this.indexScale = indexScale;
      this.position = position;
      this.sizeInBytes = size * indexScale;
      this.size = size;
   }

   public <T> ArrayBufferWithPosition(final T[] array) {
      this((Object) array, Unsafe.ARRAY_OBJECT_BASE_OFFSET, Unsafe.ARRAY_OBJECT_INDEX_SCALE, array.length, 0);
   }

   public ArrayBufferWithPosition(final byte[] array, final long position) {
      this((Object) array, Unsafe.ARRAY_BYTE_BASE_OFFSET, Unsafe.ARRAY_BYTE_INDEX_SCALE, array.length, position);
   }

   public ArrayBufferWithPosition(final short[] array, final long position) {
      this((Object) array, Unsafe.ARRAY_SHORT_BASE_OFFSET, Unsafe.ARRAY_SHORT_INDEX_SCALE, array.length, position);
   }

   public ArrayBufferWithPosition(final int[] array, final long position) {
      this((Object) array, Unsafe.ARRAY_INT_BASE_OFFSET, Unsafe.ARRAY_INT_INDEX_SCALE, array.length, position);
   }

   public ArrayBufferWithPosition(final long[] array, final long position) {
      this((Object) array, Unsafe.ARRAY_LONG_BASE_OFFSET, Unsafe.ARRAY_LONG_INDEX_SCALE, array.length, position);
   }

   public ArrayBufferWithPosition(final float[] array, final long position) {
      this((Object) array, Unsafe.ARRAY_FLOAT_BASE_OFFSET, Unsafe.ARRAY_FLOAT_INDEX_SCALE, array.length, position);
   }

   public ArrayBufferWithPosition(final double[] array, final long position) {
      this((Object) array, Unsafe.ARRAY_DOUBLE_BASE_OFFSET, Unsafe.ARRAY_DOUBLE_INDEX_SCALE, array.length, position);
   }

   public ArrayBufferWithPosition(final char[] array, final long position) {
      this((Object) array, Unsafe.ARRAY_CHAR_BASE_OFFSET, Unsafe.ARRAY_CHAR_INDEX_SCALE, array.length, position);
   }

   public ArrayBufferWithPosition(final byte[] array) {
      this((Object) array, Unsafe.ARRAY_BYTE_BASE_OFFSET, Unsafe.ARRAY_BYTE_INDEX_SCALE, array.length, 0);
   }

   //

   public ArrayBufferWithPosition(final short[] array) {
      this((Object) array, Unsafe.ARRAY_SHORT_BASE_OFFSET, Unsafe.ARRAY_SHORT_INDEX_SCALE, array.length, 0);
   }

   public ArrayBufferWithPosition(final int[] array) {
      this((Object) array, Unsafe.ARRAY_INT_BASE_OFFSET, Unsafe.ARRAY_INT_INDEX_SCALE, array.length, 0);
   }

   public ArrayBufferWithPosition(final long[] array) {
      this((Object) array, Unsafe.ARRAY_LONG_BASE_OFFSET, Unsafe.ARRAY_LONG_INDEX_SCALE, array.length, 0);
   }

   public ArrayBufferWithPosition(final float[] array) {
      this((Object) array, Unsafe.ARRAY_FLOAT_BASE_OFFSET, Unsafe.ARRAY_FLOAT_INDEX_SCALE, array.length, 0);
   }

   public ArrayBufferWithPosition(final double[] array) {
      this((Object) array, Unsafe.ARRAY_DOUBLE_BASE_OFFSET, Unsafe.ARRAY_DOUBLE_INDEX_SCALE, array.length, 0);
   }

   public ArrayBufferWithPosition(final char[] array) {
      this((Object) array, Unsafe.ARRAY_CHAR_BASE_OFFSET, Unsafe.ARRAY_CHAR_INDEX_SCALE, array.length, 0);
   }

   private static long initPositionOffset() {
      try {
         return UNSAFE.objectFieldOffset(ArrayBufferWithPosition.class.getField("position"));
      } catch (NoSuchFieldException e) {
         UnsafeAccess.rethrow(e);
         return -1;
      }
   }

   @Override
   public final long getIndexScale() {
      return indexScale;
   }

   @Override
   public final long getSize() {
      return size;
   }

   @Override
   protected final Object getArray() {
      return array;
   }

   @Override
   public final long getBaseOffset() {
      return baseOffset;
   }

   @Override
   public final long getSizeInBytes() {
      return sizeInBytes;
   }

   @Override
   public final long getPosition() {
      return UNSAFE.getLongVolatile(this, positionOffset);
   }

   @Override
   public final long getAndSetPosition(final long position) {
      return UNSAFE.getAndSetLong(this, positionOffset, position);
   }

   @Override
   public final boolean compareAndSetPosition(final long expectedPosition, final long newPosition) {
      return UNSAFE.compareAndSwapLong(this, positionOffset, expectedPosition, newPosition);
   }

   @Override
   public final long getAndIncrementPosition() {
      return UNSAFE.getAndAddLong(this, positionOffset, +1);
   }

   @Override
   public final long getAndDecrementPosition() {
      return UNSAFE.getAndAddLong(this, positionOffset, -1);
   }

   @Override
   public final long getAndAddToPosition(final long offset) {
      return UNSAFE.getAndAddLong(this, positionOffset, offset);
   }
}
