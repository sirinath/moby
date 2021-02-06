package com.sirinath.utils.buffers;

import sun.misc.Unsafe;

import java.nio.*;

public enum BufferMeta {
   Byte(Unsafe.ARRAY_BYTE_BASE_OFFSET, Unsafe.ARRAY_BYTE_INDEX_SCALE) {
      @Override
      public final Class<ByteBuffer> getBufferClass() {
         return ByteBuffer.class;
      }

      @Override
      public final Class<byte[]> getArrayClass() {
         return byte[].class;
      }
   }, Boolean(Unsafe.ARRAY_BOOLEAN_BASE_OFFSET, Unsafe.ARRAY_BOOLEAN_INDEX_SCALE) {
      @Override
      public final Class<boolean[]> getArrayClass() {
         return boolean[].class;
      }
   }, Short(Unsafe.ARRAY_SHORT_BASE_OFFSET, Unsafe.ARRAY_SHORT_INDEX_SCALE) {
      @Override
      public final Class<ShortBuffer> getBufferClass() {
         return ShortBuffer.class;
      }

      @Override
      public final Class<short[]> getArrayClass() {
         return short[].class;
      }
   }, Char(Unsafe.ARRAY_CHAR_BASE_OFFSET, Unsafe.ARRAY_CHAR_INDEX_SCALE) {
      @Override
      public final Class<CharBuffer> getBufferClass() {
         return CharBuffer.class;
      }

      @Override
      public final Class<char[]> getArrayClass() {
         return char[].class;
      }
   }, Int(Unsafe.ARRAY_INT_BASE_OFFSET, Unsafe.ARRAY_INT_INDEX_SCALE) {
      @Override
      public final Class<IntBuffer> getBufferClass() {
         return IntBuffer.class;
      }

      @Override
      public final Class<int[]> getArrayClass() {
         return int[].class;
      }
   }, Float(Unsafe.ARRAY_FLOAT_BASE_OFFSET, Unsafe.ARRAY_FLOAT_INDEX_SCALE) {
      @Override
      public final Class<FloatBuffer> getBufferClass() {
         return FloatBuffer.class;
      }

      @Override
      public final Class<float[]> getArrayClass() {
         return float[].class;
      }
   }, Long(Unsafe.ARRAY_LONG_BASE_OFFSET, Unsafe.ARRAY_LONG_INDEX_SCALE) {
      @Override
      public final Class<LongBuffer> getBufferClass() {
         return LongBuffer.class;
      }

      @Override
      public final Class<long[]> getArrayClass() {
         return long[].class;
      }
   }, Double(Unsafe.ARRAY_DOUBLE_BASE_OFFSET, Unsafe.ARRAY_DOUBLE_INDEX_SCALE) {
      @Override
      public final Class<DoubleBuffer> getBufferClass() {
         return DoubleBuffer.class;
      }

      @Override
      public final Class<double[]> getArrayClass() {
         return double[].class;
      }
   }, Object(Unsafe.ARRAY_OBJECT_BASE_OFFSET, Unsafe.ARRAY_OBJECT_INDEX_SCALE) {
      @Override
      public final Class<Object[]> getArrayClass() {
         return Object[].class;
      }
   };

   private final long baseOffset;
   private final long indexScale;

   BufferMeta(final long baseOffset, final long indexScale) {
      this.baseOffset = baseOffset;
      this.indexScale = indexScale;
   }

   public final long getIndexScale() {
      return indexScale;
   }

   public final long getBaseOffset() {
      return baseOffset;
   }

   public Class getBufferClass() {
      return null;
   }

   public Class getArrayClass() {
      return null;
   }
}
