package com.sirinath.utils.buffers;

import com.sirinath.utils.UnsafeAccess;
import sun.misc.Unsafe;

import java.nio.Buffer;
import java.nio.ByteBuffer;

public class ResizableDirectBuffer extends AbstractDirectBuffer {
   private static final Unsafe UNSAFE = UnsafeAccess.UNSAFE;

   private long address;
   private long indexScale;
   private long size;
   private long   sizeInBytes;

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

   private ResizableDirectBuffer(final long address, final long indexScale, final long size, final long sizeInBytes) {
      this.address = address;
      this.indexScale = indexScale;
      this.size = size;
      this.sizeInBytes = sizeInBytes;

      this.freeMemory = new FreeMemory(this, address);
   }

   private ResizableDirectBuffer(final long indexScale, final long size, final long sizeInBytes) {
      this(UNSAFE.allocateMemory(sizeInBytes), indexScale, size,
           sizeInBytes);
   }

   public ResizableDirectBuffer(final BufferMeta bufferMeta, final long size) {
      this(bufferMeta.getIndexScale(), size, size * bufferMeta.getIndexScale());
   }

   private synchronized void wrap(final long address, final long indexScale, final long size,
                                    final long sizeInBytes) {
      this.address = address;
      this.indexScale = indexScale;
      this.size = size;
      this.sizeInBytes = sizeInBytes;

      freeMemory.setAddress(address);
   }

   private synchronized void reset(final long indexScale, final long size, final long sizeInBytes) {
      UNSAFE.freeMemory(this.address);

      wrap(UNSAFE.allocateMemory(sizeInBytes), indexScale, size, sizeInBytes);
   }

   public final void reset(final BufferMeta bufferMeta, final long size) {
      reset(bufferMeta.getIndexScale(), size, size * bufferMeta.getIndexScale());
   }

   public synchronized void resize(final int newSize) {
      address = UNSAFE.reallocateMemory(address, newSize);
   }
}
