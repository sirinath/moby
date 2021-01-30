package com.sirinath.utils.buffers;

import sun.misc.Unsafe;

import java.nio.*;
import java.nio.Buffer;

public class ArrayBuffer extends AbstractArrayBuffer {
   private final Object array;
   private final long   baseOffset;
   private final long   indexScale;
   private final long   size;
   private final long   sizeInBytes;

   @Override
   public final Object getArray() {
      return array;
   }

   @Override
   public final long getSize() {
      return size;
   }

   @Override
   public final long getSizeInBytes() {
      return sizeInBytes;
   }

   @Override
   public final long getBaseOffset() {
      return baseOffset;
   }

   @Override
   public final long getIndexScale() {
      return indexScale;
   }

   public <T> ArrayBuffer(final T[] array) {
      this((Object) array, Unsafe.ARRAY_OBJECT_BASE_OFFSET, Unsafe.ARRAY_OBJECT_INDEX_SCALE, array.length);
   }

   public ArrayBuffer(final byte[] array) {
      this((Object) array, Unsafe.ARRAY_BYTE_BASE_OFFSET, Unsafe.ARRAY_BYTE_INDEX_SCALE, array.length);
   }

   public ArrayBuffer(final boolean[] array) {
      this((Object) array, Unsafe.ARRAY_BOOLEAN_BASE_OFFSET, Unsafe.ARRAY_BOOLEAN_INDEX_SCALE, array.length);
   }

   public ArrayBuffer(final short[] array) {
      this((Object) array, Unsafe.ARRAY_SHORT_BASE_OFFSET, Unsafe.ARRAY_SHORT_INDEX_SCALE, array.length);
   }

   public ArrayBuffer(final int[] array) {
      this((Object) array, Unsafe.ARRAY_INT_BASE_OFFSET, Unsafe.ARRAY_INT_INDEX_SCALE, array.length);
   }

   public ArrayBuffer(final long[] array) {
      this((Object) array, Unsafe.ARRAY_LONG_BASE_OFFSET, Unsafe.ARRAY_LONG_INDEX_SCALE, array.length);
   }

   public ArrayBuffer(final float[] array) {
      this((Object) array, Unsafe.ARRAY_FLOAT_BASE_OFFSET, Unsafe.ARRAY_FLOAT_INDEX_SCALE, array.length);
   }

   public ArrayBuffer(final double[] array) {
      this((Object) array, Unsafe.ARRAY_DOUBLE_BASE_OFFSET, Unsafe.ARRAY_DOUBLE_INDEX_SCALE, array.length);
   }

   public ArrayBuffer(final char[] array) {
      this((Object) array, Unsafe.ARRAY_CHAR_BASE_OFFSET, Unsafe.ARRAY_CHAR_INDEX_SCALE, array.length);
   }

   private static <T extends Buffer> T checkBuffer(final T buffer) {
      if (buffer.hasArray())
         return buffer;
      else
         throw new IllegalArgumentException("Buffer needs to have a backing array.");
   }

   public ArrayBuffer(final ByteBuffer buffer) throws IllegalArgumentException {
      this((Object) checkBuffer(buffer).array(),
            Unsafe.ARRAY_BYTE_BASE_OFFSET,
           Unsafe.ARRAY_BYTE_INDEX_SCALE,
           buffer.capacity());
   }

   public ArrayBuffer(final ShortBuffer buffer) throws IllegalArgumentException {
      this((Object) checkBuffer(buffer).array(), Unsafe.ARRAY_SHORT_BASE_OFFSET, Unsafe.ARRAY_SHORT_INDEX_SCALE,
           buffer.capacity());
   }

   public ArrayBuffer(final IntBuffer buffer) throws IllegalArgumentException {
      this((Object) checkBuffer(buffer).array(), Unsafe.ARRAY_INT_BASE_OFFSET, Unsafe.ARRAY_INT_INDEX_SCALE, buffer.capacity());
   }

   public ArrayBuffer(final LongBuffer buffer) throws IllegalArgumentException {
      this((Object) checkBuffer(buffer).array(), Unsafe.ARRAY_LONG_BASE_OFFSET, Unsafe.ARRAY_LONG_INDEX_SCALE,
           buffer.capacity());
   }

   public ArrayBuffer(final FloatBuffer buffer) throws IllegalArgumentException {
      this((Object) checkBuffer(buffer).array(), Unsafe.ARRAY_FLOAT_BASE_OFFSET, Unsafe.ARRAY_FLOAT_INDEX_SCALE, buffer
          .capacity());
   }

   public ArrayBuffer(final DoubleBuffer buffer) throws IllegalArgumentException {
      this((Object) checkBuffer(buffer).array(), Unsafe.ARRAY_DOUBLE_BASE_OFFSET, Unsafe.ARRAY_DOUBLE_INDEX_SCALE, buffer.capacity());
   }

   public ArrayBuffer(final CharBuffer buffer) throws IllegalArgumentException {
      this((Object) checkBuffer(buffer).array(), Unsafe.ARRAY_CHAR_BASE_OFFSET, Unsafe.ARRAY_CHAR_INDEX_SCALE, buffer.capacity());
   }

   protected ArrayBuffer(final Object array, final long baseOffset, final long indexScale, final long size) {
      this.array = array;
      this.baseOffset = baseOffset;
      this.indexScale = indexScale;
      this.sizeInBytes = size * indexScale;
      this.size = size;
   }
}
