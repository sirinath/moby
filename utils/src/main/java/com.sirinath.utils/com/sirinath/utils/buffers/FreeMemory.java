package com.sirinath.utils.buffers;

import com.sirinath.utils.UnsafeAccess;
import sun.misc.Unsafe;

import java.lang.ref.Cleaner;
import java.lang.ref.Cleaner.Cleanable;

public final class FreeMemory implements Runnable {
   private static final Unsafe UNSAFE = UnsafeAccess.UNSAFE;

   private       long      address;
   private final Cleanable cleanable;

   public final long getAddress() {
      return address;
   }

   public final void setAddress(final long address) {
      this.address = address;
   }

   public <T> FreeMemory(final T obj, final long address) {
      this.address = address;
      this.cleanable = Cleaner.create().register(obj, this);
   }

   public <T> FreeMemory(final T obj) {
      this(obj, 0);
   }

   @Override
   public final void run() {
      UNSAFE.freeMemory(address);
   }
}
