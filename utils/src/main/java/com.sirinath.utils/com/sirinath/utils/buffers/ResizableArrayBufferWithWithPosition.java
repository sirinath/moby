package com.sirinath.utils.buffers;

import com.sirinath.utils.UnsafeAccess;

public final class ResizableArrayBufferWithWithPosition extends ResizableArrayBuffer implements BufferWithPosition {
   private volatile     long position;
   private static final long positionOffset = UnsafeAccess.getFieldOffset("position",
                                                                          ResizableArrayBufferWithWithPosition.class);

   @Override
   public final long getPositionFieldOffset() {
      return positionOffset;
   }

   public <T> ResizableArrayBufferWithWithPosition(final T[] array, final long position) {
      super(array);
      this.position = position;
   }

   public ResizableArrayBufferWithWithPosition(final byte[] array, final long position) {
      super(array);
      this.position = position;
   }

   public ResizableArrayBufferWithWithPosition(final boolean[] array, final long position) {
      super(array);
      this.position = position;
   }

   public ResizableArrayBufferWithWithPosition(final short[] array, final long position) {
      super(array);
      this.position = position;
   }

   public ResizableArrayBufferWithWithPosition(final int[] array, final long position) {
      super(array);
      this.position = position;
   }

   public ResizableArrayBufferWithWithPosition(final long[] array, final long position) {
      super(array);
      this.position = position;
   }

   public ResizableArrayBufferWithWithPosition(final float[] array, final long position) {
      super(array);
      this.position = position;
   }

   public ResizableArrayBufferWithWithPosition(final double[] array, final long position) {
      super(array);
      this.position = position;
   }

   public ResizableArrayBufferWithWithPosition(final char[] array, final long position) {
      super(array);
      this.position = position;
   }

   public <T> ResizableArrayBufferWithWithPosition(final T[] array) {
      this(array, 0);
   }

   public ResizableArrayBufferWithWithPosition(final byte[] array) {
      this(array, 0);
   }

   public ResizableArrayBufferWithWithPosition(final boolean[] array) {
      this(array, 0);
   }

   public ResizableArrayBufferWithWithPosition(final short[] array) {
      this(array, 0);
   }

   public ResizableArrayBufferWithWithPosition(final int[] array) {
      this(array, 0);
   }

   public ResizableArrayBufferWithWithPosition(final long[] array) {
      this(array, 0);
   }

   public ResizableArrayBufferWithWithPosition(final float[] array) {
      this(array, 0);
   }

   public ResizableArrayBufferWithWithPosition(final double[] array) {
      this(array, 0);
   }

   public ResizableArrayBufferWithWithPosition(final char[] array) {
      this(array, 0);
   }
}
