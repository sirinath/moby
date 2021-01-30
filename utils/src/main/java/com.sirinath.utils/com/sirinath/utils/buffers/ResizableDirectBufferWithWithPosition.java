package com.sirinath.utils.buffers;

import com.sirinath.utils.UnsafeAccess;

public class ResizableDirectBufferWithWithPosition extends ResizableDirectBuffer implements BufferWithPosition {
   private volatile     long position;
   private static final long positionOffset = UnsafeAccess.getFieldOffset("position",
                                                                          ResizableArrayBufferWithWithPosition.class);

   @Override
   public final long getPositionFieldOffset() {
      return positionOffset;
   }

   public ResizableDirectBufferWithWithPosition(final BufferMeta bufferMeta, final long size) {
      super(bufferMeta, size);
   }
}