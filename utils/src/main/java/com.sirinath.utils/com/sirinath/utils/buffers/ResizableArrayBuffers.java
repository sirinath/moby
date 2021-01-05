package com.sirinath.utils.buffers;

import sun.misc.Unsafe;

import java.util.Arrays;

public class ResizableArrayBuffers extends AbstractArrayBuffer {
   private static final Sizer<Object[]> ObjSizer    = Arrays::copyOf;
   private static final Sizer<byte[]>   ByteSizer   = Arrays::copyOf;
   private static final Sizer<short[]>  ShortSizer  = Arrays::copyOf;
   private static final Sizer<int[]>    IntSizer    = Arrays::copyOf;
   private static final Sizer<long[]>   LongSizer   = Arrays::copyOf;
   private static final Sizer<float[]>  FloatSizer  = Arrays::copyOf;
   private static final Sizer<double[]> DoubleSizer = Arrays::copyOf;
   private static final Sizer<char[]>   CharSizer   = Arrays::copyOf;
   private              Object          array;
   private              Sizer           sizer;
   private              long            baseOffset;
   private              long            indexScale;
   private              long            sizeInBytes;
   private              long            size;

   public <T> ResizableArrayBuffers(final T[] array) {
      this((Object) array, ObjSizer, Unsafe.ARRAY_OBJECT_BASE_OFFSET, Unsafe.ARRAY_OBJECT_INDEX_SCALE, array.length);
   }

   private ResizableArrayBuffers(final Object array, final Sizer<?> sizer, final long baseOffset,
                                 final long indexScale, final long size) {
      this.array = array;
      this.sizer = sizer;
      this.baseOffset = baseOffset;
      this.indexScale = indexScale;
      this.sizeInBytes = size * indexScale;
      this.size = size;
   }

   public ResizableArrayBuffers(final byte[] array) {
      this((Object) array, ByteSizer, Unsafe.ARRAY_BYTE_BASE_OFFSET, Unsafe.ARRAY_BYTE_INDEX_SCALE, array.length);
   }

   public ResizableArrayBuffers(final short[] array) {
      this((Object) array, ShortSizer, Unsafe.ARRAY_SHORT_BASE_OFFSET, Unsafe.ARRAY_SHORT_INDEX_SCALE, array.length);
   }

   public ResizableArrayBuffers(final int[] array) {
      this((Object) array, IntSizer, Unsafe.ARRAY_INT_BASE_OFFSET, Unsafe.ARRAY_INT_INDEX_SCALE, array.length);
   }

   public ResizableArrayBuffers(final long[] array) {
      this((Object) array, LongSizer, Unsafe.ARRAY_LONG_BASE_OFFSET, Unsafe.ARRAY_LONG_INDEX_SCALE, array.length);
   }

   public ResizableArrayBuffers(final float[] array) {
      this((Object) array, FloatSizer, Unsafe.ARRAY_FLOAT_BASE_OFFSET, Unsafe.ARRAY_FLOAT_INDEX_SCALE, array.length);
   }

   public ResizableArrayBuffers(final double[] array) {
      this((Object) array, DoubleSizer, Unsafe.ARRAY_DOUBLE_BASE_OFFSET, Unsafe.ARRAY_DOUBLE_INDEX_SCALE, array.length);
   }

   public ResizableArrayBuffers(final char[] array) {
      this((Object) array, CharSizer, Unsafe.ARRAY_CHAR_BASE_OFFSET, Unsafe.ARRAY_CHAR_INDEX_SCALE, array.length);
   }

   public synchronized void resize(final int newSize) {
      array = sizer.apply(array, newSize);
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

   public final <T> void wrap(final T[] array) {
      wrap((Object) array, ObjSizer, Unsafe.ARRAY_OBJECT_BASE_OFFSET, Unsafe.ARRAY_OBJECT_INDEX_SCALE, array.length);
   }

   private synchronized void wrap(final Object array, final Sizer<?> sizer, final long baseOffset,
                                  final long indexScale, final long size) {
      this.array = array;
      this.sizer = sizer;
      this.baseOffset = baseOffset;
      this.indexScale = indexScale;
      this.sizeInBytes = size * indexScale;
      this.size = size;
   }

   public final void wrap(final byte[] array) {
      wrap((Object) array, ByteSizer, Unsafe.ARRAY_BYTE_BASE_OFFSET, Unsafe.ARRAY_BYTE_INDEX_SCALE, array.length);
   }

   public final void wrap(final short[] array) {
      wrap((Object) array, ShortSizer, Unsafe.ARRAY_SHORT_BASE_OFFSET, Unsafe.ARRAY_SHORT_INDEX_SCALE, array.length);
   }

   public final void wrap(final int[] array) {
      wrap((Object) array, IntSizer, Unsafe.ARRAY_INT_BASE_OFFSET, Unsafe.ARRAY_INT_INDEX_SCALE, array.length);
   }

   public final void wrap(final long[] array) {
      wrap((Object) array, LongSizer, Unsafe.ARRAY_LONG_BASE_OFFSET, Unsafe.ARRAY_LONG_INDEX_SCALE, array.length);
   }

   public final void wrap(final float[] array) {
      wrap((Object) array, IntSizer, Unsafe.ARRAY_FLOAT_BASE_OFFSET, Unsafe.ARRAY_FLOAT_INDEX_SCALE, array.length);
   }

   public final void wrap(final double[] array) {
      wrap((Object) array, IntSizer, Unsafe.ARRAY_DOUBLE_BASE_OFFSET, Unsafe.ARRAY_DOUBLE_INDEX_SCALE, array.length);
   }

   public final void wrap(final char[] array) {
      wrap((Object) array, CharSizer, Unsafe.ARRAY_CHAR_BASE_OFFSET, Unsafe.ARRAY_CHAR_INDEX_SCALE, array.length);
   }

   private interface Sizer<T> {
      T apply(final T array, final int newSize);
   }
}
