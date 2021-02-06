package com.sirinath.utils.buffers;

import com.sirinath.utils.UnsafeAccess;
import sun.misc.Unsafe;

public class DirectBufferWithWithPosition extends DirectBuffer implements BufferWithPosition {
   private static final Unsafe UNSAFE = UnsafeAccess.UNSAFE;

   private volatile     long position;
   private static final long positionOffset = UnsafeAccess.getFieldOffset("position",
                                                                          DirectBufferWithWithPosition.class);

   @Override
   public final long getPositionFieldOffset() {
      return positionOffset;
   }

   public DirectBufferWithWithPosition(final BufferMeta bufferMeta, final long size, final long position) {
      super(bufferMeta, size);
      this.position = position;
   }
}
