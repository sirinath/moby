package com.sirinath.utils.buffers;

import com.sirinath.utils.UnsafeAccess;
import sun.misc.Unsafe;

import java.nio.*;

public final class ArrayBufferWithWithPosition extends ArrayBuffer implements BufferWithPosition {
   private static final Unsafe UNSAFE = UnsafeAccess.UNSAFE;

   private volatile     long position;
   private static final long positionOffset = UnsafeAccess.getFieldOffset("position",
                                                                          ArrayBufferWithWithPosition.class);

   @Override
   public final long getPositionFieldOffset() {
      return positionOffset;
   }

   public <T> ArrayBufferWithWithPosition(final T[] array, final long position) {
      super(array);
      this.position = position;
   }

   public ArrayBufferWithWithPosition(final byte[] array, final long position) {
      super(array);
      this.position = position;
   }

   public ArrayBufferWithWithPosition(final boolean[] array, final long position) {
      super(array);
      this.position = position;
   }

   public ArrayBufferWithWithPosition(final short[] array, final long position) {
      super(array);
      this.position = position;
   }

   public ArrayBufferWithWithPosition(final int[] array, final long position) {
      super(array);
      this.position = position;
   }

   public ArrayBufferWithWithPosition(final long[] array, final long position) {
      super(array);
      this.position = position;
   }

   public ArrayBufferWithWithPosition(final float[] array, final long position) {
      super(array);
      this.position = position;
   }

   public ArrayBufferWithWithPosition(final double[] array, final long position) {
      super(array);
      this.position = position;
   }

   public ArrayBufferWithWithPosition(final char[] array, final long position) {
      super(array);
      this.position = position;
   }

   public <T> ArrayBufferWithWithPosition(final T[] array) {
      this(array, 0);
   }

   public ArrayBufferWithWithPosition(final byte[] array) {
      this(array, 0);
   }

   public ArrayBufferWithWithPosition(final boolean[] array) {
      this(array, 0);
   }

   public ArrayBufferWithWithPosition(final short[] array) {
      this(array, 0);
   }

   public ArrayBufferWithWithPosition(final int[] array) {
      this(array, 0);
   }

   public ArrayBufferWithWithPosition(final long[] array) {
      this(array, 0);
   }

   public ArrayBufferWithWithPosition(final float[] array) {
      this(array, 0);
   }

   public ArrayBufferWithWithPosition(final double[] array) {
      this(array, 0);
   }

   public ArrayBufferWithWithPosition(final char[] array) {
      this(array, 0);
   }

   public ArrayBufferWithWithPosition(final ByteBuffer buffer, final long position) {
      super(buffer);
      this.position = position;
   }

   public ArrayBufferWithWithPosition(final ShortBuffer buffer, final long position) {
      super(buffer);
      this.position = position;
   }

   public ArrayBufferWithWithPosition(final IntBuffer buffer, final long position) {
      super(buffer);
      this.position = position;
   }

   public ArrayBufferWithWithPosition(final LongBuffer buffer, final long position) {
      super(buffer);
      this.position = position;
   }

   public ArrayBufferWithWithPosition(final FloatBuffer buffer, final long position) {
      super(buffer);
      this.position = position;
   }

   public ArrayBufferWithWithPosition(final DoubleBuffer buffer, final long position) {
      super(buffer);
      this.position = position;
   }

   public ArrayBufferWithWithPosition(final CharBuffer buffer, final long position) {
      super(buffer);
      this.position = position;
   }

   public ArrayBufferWithWithPosition(final ByteBuffer buffer) {
      super(buffer);
      this.position = buffer.position();
   }

   public ArrayBufferWithWithPosition(final ShortBuffer buffer) {
      super(buffer);
      this.position = buffer.position();
   }

   public ArrayBufferWithWithPosition(final IntBuffer buffer) {
      super(buffer);
      this.position = buffer.position();
   }

   public ArrayBufferWithWithPosition(final LongBuffer buffer) {
      super(buffer);
      this.position = buffer.position();
   }

   public ArrayBufferWithWithPosition(final FloatBuffer buffer) {
      super(buffer);
      this.position = buffer.position();
   }

   public ArrayBufferWithWithPosition(final DoubleBuffer buffer) {
      super(buffer);
      this.position = buffer.position();
   }

   public ArrayBufferWithWithPosition(final CharBuffer buffer) {
      super(buffer);
      this.position = buffer.position();
   }
}
