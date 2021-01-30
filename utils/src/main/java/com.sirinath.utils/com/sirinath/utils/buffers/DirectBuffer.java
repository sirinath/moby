package com.sirinath.utils.buffers;

import com.sirinath.utils.UnsafeAccess;

import sun.misc.Unsafe;

public class DirectBuffer extends AbstractDirectBuffer {
   private static final Unsafe UNSAFE = UnsafeAccess.UNSAFE;

   private final long address;
   private final long indexScale;
   private final long size;
   private final long   sizeInBytes;

   private final FreeMemory freeMemory;

   @Override
   public final long getAddress() {
      return address;
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

   private DirectBuffer(final long address, final long indexScale, final long size, final long sizeInBytes) {
      this.address = address;
      this.indexScale = indexScale;
      this.size = size;
      this.sizeInBytes = sizeInBytes;

      this.freeMemory = new FreeMemory(this, address);
   }

   private DirectBuffer(final long indexScale, final long size, final long sizeInBytes) {
      this(UNSAFE.allocateMemory(sizeInBytes), indexScale, size,
           sizeInBytes);
   }

   public DirectBuffer(final BufferMeta bufferMeta, final long size) {
      this(bufferMeta.getIndexScale(), size, size * bufferMeta.getIndexScale());
   }
}
