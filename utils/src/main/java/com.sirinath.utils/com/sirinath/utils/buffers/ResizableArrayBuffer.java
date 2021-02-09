package com.sirinath.utils.buffers;

import sun.misc.Unsafe;

import java.util.Arrays;

public class ResizableArrayBuffer extends AbstractArrayBuffer {
   private interface Sizer<T> {
      T apply(final T array, final int newSize);
   }

   private static final Sizer<byte[]>    byteSizer    = Arrays::copyOf;
   private static final Sizer<boolean[]> booleanSizer = Arrays::copyOf;
   private static final Sizer<char[]>    charSizer    = Arrays::copyOf;
   private static final Sizer<double[]>  doubleSizer  = Arrays::copyOf;
   private static final Sizer<float[]>   floatSizer   = Arrays::copyOf;
   private static final Sizer<int[]>     intSizer     = Arrays::copyOf;
   private static final Sizer<long[]>    longSizer    = Arrays::copyOf;
   private static final Sizer<Object[]>  objSizer     = Arrays::copyOf;
   private static final Sizer<short[]>   shortSizer   = Arrays::copyOf;

   private Sizer sizer;

   private Object array;
   private long   baseOffset;
   private long   indexScale;
   private long   size;
   private long   sizeInBytes;

   @Override
   public final Object getArray() {
      return array;
   }

   @Override
   public final long getBaseOffset() {
      return baseOffset;
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
   public final long getSizeInBytes() {
      return sizeInBytes;
   }

   public <T> ResizableArrayBuffer(final T[] array) {
      this(objSizer, (Object) array, Unsafe.ARRAY_OBJECT_BASE_OFFSET, Unsafe.ARRAY_OBJECT_INDEX_SCALE, array.length);
   }

   public ResizableArrayBuffer(final byte[] array) {
      this(byteSizer, (Object) array, Unsafe.ARRAY_BYTE_BASE_OFFSET, Unsafe.ARRAY_BYTE_INDEX_SCALE, array.length);
   }

   public ResizableArrayBuffer(final boolean[] array) {
      this(booleanSizer,
           (Object) array,
           Unsafe.ARRAY_BOOLEAN_BASE_OFFSET,
           Unsafe.ARRAY_BOOLEAN_INDEX_SCALE,
           array.length);
   }

   public ResizableArrayBuffer(final short[] array) {
      this(shortSizer, (Object) array, Unsafe.ARRAY_SHORT_BASE_OFFSET, Unsafe.ARRAY_SHORT_INDEX_SCALE, array.length);
   }

   public ResizableArrayBuffer(final int[] array) {
      this(intSizer, (Object) array, Unsafe.ARRAY_INT_BASE_OFFSET, Unsafe.ARRAY_INT_INDEX_SCALE, array.length);
   }

   public ResizableArrayBuffer(final long[] array) {
      this(longSizer, (Object) array, Unsafe.ARRAY_LONG_BASE_OFFSET, Unsafe.ARRAY_LONG_INDEX_SCALE, array.length);
   }

   public ResizableArrayBuffer(final float[] array) {
      this(floatSizer, (Object) array, Unsafe.ARRAY_FLOAT_BASE_OFFSET, Unsafe.ARRAY_FLOAT_INDEX_SCALE, array.length);
   }

   public ResizableArrayBuffer(final double[] array) {
      this(doubleSizer, (Object) array, Unsafe.ARRAY_DOUBLE_BASE_OFFSET, Unsafe.ARRAY_DOUBLE_INDEX_SCALE, array.length);
   }

   public ResizableArrayBuffer(final char[] array) {
      this(charSizer, (Object) array, Unsafe.ARRAY_CHAR_BASE_OFFSET, Unsafe.ARRAY_CHAR_INDEX_SCALE, array.length);
   }

   private ResizableArrayBuffer(final Sizer<?> sizer,
                                final Object array,
                                final long baseOffset,
                                final long indexScale,
                                final long size) {
      this.sizer = sizer;
      this.array = array;
      this.baseOffset = baseOffset;
      this.indexScale = indexScale;
      this.sizeInBytes = size * indexScale;
      this.size = size;
   }

   public final <T> void wrap(final T[] array) {
      wrap(objSizer, (Object) array, Unsafe.ARRAY_OBJECT_BASE_OFFSET, Unsafe.ARRAY_OBJECT_INDEX_SCALE, array.length);
   }

   public final void wrap(final byte[] array) {
      wrap(byteSizer, (Object) array, Unsafe.ARRAY_BYTE_BASE_OFFSET, Unsafe.ARRAY_BYTE_INDEX_SCALE, array.length);
   }

   public final void wrap(final boolean[] array) {
      wrap(booleanSizer, (Object) array, Unsafe.ARRAY_BYTE_BASE_OFFSET, Unsafe.ARRAY_BYTE_INDEX_SCALE, array.length);
   }

   public final void wrap(final short[] array) {
      wrap(shortSizer, (Object) array, Unsafe.ARRAY_SHORT_BASE_OFFSET, Unsafe.ARRAY_SHORT_INDEX_SCALE, array.length);
   }

   public final void wrap(final int[] array) {
      wrap(intSizer, (Object) array, Unsafe.ARRAY_INT_BASE_OFFSET, Unsafe.ARRAY_INT_INDEX_SCALE, array.length);
   }

   public final void wrap(final long[] array) {
      wrap(longSizer, (Object) array, Unsafe.ARRAY_LONG_BASE_OFFSET, Unsafe.ARRAY_LONG_INDEX_SCALE, array.length);
   }

   public final void wrap(final float[] array) {
      wrap(intSizer, (Object) array, Unsafe.ARRAY_FLOAT_BASE_OFFSET, Unsafe.ARRAY_FLOAT_INDEX_SCALE, array.length);
   }

   public final void wrap(final double[] array) {
      wrap(intSizer, (Object) array, Unsafe.ARRAY_DOUBLE_BASE_OFFSET, Unsafe.ARRAY_DOUBLE_INDEX_SCALE, array.length);
   }

   public final void wrap(final char[] array) {
      wrap(charSizer, (Object) array, Unsafe.ARRAY_CHAR_BASE_OFFSET, Unsafe.ARRAY_CHAR_INDEX_SCALE, array.length);
   }

   private synchronized void wrap(final Sizer<?> sizer,
                                  final Object array,
                                  final long baseOffset,
                                  final long indexScale,
                                  final long size) {
      this.sizer = sizer;
      this.array = array;
      this.baseOffset = baseOffset;
      this.indexScale = indexScale;
      this.sizeInBytes = size * indexScale;
      this.size = size;
   }

   public synchronized void resize(final int newSize) {
      array = sizer.apply(array, newSize);
   }
}
