package com.sirinath.utils.buffers;

import java.nio.*;

public final class NativeBufferUtil {
   public static ByteBuffer createByteArrayBuffer(final int capacity) {
      return ByteBuffer.allocate(capacity).order(ByteOrder.nativeOrder());
   }

   public static ShortBuffer createShortArrayBuffer(final int capacity) {
      return ByteBuffer.allocate(capacity * Short.BYTES).order(ByteOrder.nativeOrder()).asShortBuffer();
   }

   public static CharBuffer createCharArrayBuffer(final int capacity) {
      return ByteBuffer.allocate(capacity * Character.BYTES).order(ByteOrder.nativeOrder()).asCharBuffer();
   }

   public static IntBuffer createIntArrayBuffer(final int capacity) {
      return ByteBuffer.allocate(capacity * Integer.BYTES).order(ByteOrder.nativeOrder()).asIntBuffer();
   }

   public static LongBuffer createLongArrayBuffer(final int capacity) {
      return ByteBuffer.allocate(capacity * Long.BYTES).order(ByteOrder.nativeOrder()).asLongBuffer();
   }

   public static FloatBuffer createFloatArrayBuffer(final int capacity) {
      return ByteBuffer.allocate(capacity * Float.BYTES).order(ByteOrder.nativeOrder()).asFloatBuffer();
   }

   public static DoubleBuffer createDoubleArrayBuffer(final int capacity) {
      return ByteBuffer.allocate(capacity * Double.BYTES).order(ByteOrder.nativeOrder()).asDoubleBuffer();
   }

   public static ByteBuffer createByteDirectBuffer(final int capacity) {
      return ByteBuffer.allocateDirect(capacity).order(ByteOrder.nativeOrder());
   }

   public static ShortBuffer createShortDirectBuffer(final int capacity) {
      return ByteBuffer.allocateDirect(capacity * Short.BYTES).order(ByteOrder.nativeOrder()).asShortBuffer();
   }

   public static CharBuffer createCharDirectBuffer(final int capacity) {
      return ByteBuffer.allocateDirect(capacity * Character.BYTES).order(ByteOrder.nativeOrder()).asCharBuffer();
   }

   public static IntBuffer createIntDirectBuffer(final int capacity) {
      return ByteBuffer.allocateDirect(capacity * Integer.BYTES).order(ByteOrder.nativeOrder()).asIntBuffer();
   }

   public static LongBuffer createLongDirectBuffer(final int capacity) {
      return ByteBuffer.allocateDirect(capacity * Long.BYTES).order(ByteOrder.nativeOrder()).asLongBuffer();
   }

   public static FloatBuffer createFloatDirectBuffer(final int capacity) {
      return ByteBuffer.allocateDirect(capacity * Float.BYTES).order(ByteOrder.nativeOrder()).asFloatBuffer();
   }

   public static DoubleBuffer createDoubleDirectBuffer(final int capacity) {
      return ByteBuffer.allocateDirect(capacity * Double.BYTES).order(ByteOrder.nativeOrder()).asDoubleBuffer();
   }

   private NativeBufferUtil() {}
}
