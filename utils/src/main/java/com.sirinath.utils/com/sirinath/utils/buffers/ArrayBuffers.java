package com.sirinath.utils.buffers;

import sun.misc.Unsafe;

public class ArrayBuffers extends AbstractArrayBuffer {
   private final Object array;
   private final long   baseOffset;
   private final long   indexScale;
   private final long   sizeInBytes;
   private final long   size;

   public <T> ArrayBuffers(final T[] array) {
      this((Object) array, Unsafe.ARRAY_OBJECT_BASE_OFFSET, Unsafe.ARRAY_OBJECT_INDEX_SCALE, array.length);
   }

   private ArrayBuffers(final Object array, final long baseOffset, final long indexScale, final long size) {
      this.array = array;
      this.baseOffset = baseOffset;
      this.indexScale = indexScale;
      this.sizeInBytes = size * indexScale;
      this.size = size;
   }

   public ArrayBuffers(final byte[] array) {
      this((Object) array, Unsafe.ARRAY_BYTE_BASE_OFFSET, Unsafe.ARRAY_BYTE_INDEX_SCALE, array.length);
   }

   public ArrayBuffers(final short[] array) {
      this((Object) array, Unsafe.ARRAY_SHORT_BASE_OFFSET, Unsafe.ARRAY_SHORT_INDEX_SCALE, array.length);
   }

   public ArrayBuffers(final int[] array) {
      this((Object) array, Unsafe.ARRAY_INT_BASE_OFFSET, Unsafe.ARRAY_INT_INDEX_SCALE, array.length);
   }

   public ArrayBuffers(final long[] array) {
      this((Object) array, Unsafe.ARRAY_LONG_BASE_OFFSET, Unsafe.ARRAY_LONG_INDEX_SCALE, array.length);
   }

   public ArrayBuffers(final float[] array) {
      this((Object) array, Unsafe.ARRAY_FLOAT_BASE_OFFSET, Unsafe.ARRAY_FLOAT_INDEX_SCALE, array.length);
   }

   public ArrayBuffers(final double[] array) {
      this((Object) array, Unsafe.ARRAY_DOUBLE_BASE_OFFSET, Unsafe.ARRAY_DOUBLE_INDEX_SCALE, array.length);
   }

   public ArrayBuffers(final char[] array) {
      this((Object) array, Unsafe.ARRAY_CHAR_BASE_OFFSET, Unsafe.ARRAY_CHAR_INDEX_SCALE, array.length);
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
   public long getSizeInBytes() {
      return sizeInBytes;
   }
}
