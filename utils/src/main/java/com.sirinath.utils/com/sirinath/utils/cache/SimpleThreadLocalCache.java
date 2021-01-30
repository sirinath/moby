package com.sirinath.utils.cache;

import com.conversantmedia.util.concurrent.DisruptorBlockingQueue;

import java.lang.invoke.VarHandle;
import java.util.function.Supplier;

public final class SimpleThreadLocalCache<T> extends AbstractSimpleCache<T> {
   private final ThreadLocal<DisruptorBlockingQueue<T>> threadLocalQueue =
         new ThreadLocal<DisruptorBlockingQueue<T>>() {
            @Override
            protected DisruptorBlockingQueue<T> initialValue() {
               return new DisruptorBlockingQueue<>(getSize());
            }
         };
   private       DisruptorBlockingQueue<T>              lastQueue        = threadLocalQueue.get();
   private       Thread                                 lastThread       = Thread.currentThread();

   @Override
   protected final DisruptorBlockingQueue<T> getQueue() {
      final Thread currentThread = Thread.currentThread();

      DisruptorBlockingQueue<T> lastQueue  = this.lastQueue;
      final Thread              lastThread = this.lastThread;
      VarHandle.acquireFence();

      if (currentThread != lastThread) {
         lastQueue = threadLocalQueue.get();

         this.lastThread = currentThread;
         this.lastQueue = lastQueue;
         VarHandle.releaseFence();
      }

      return lastQueue;
   }

   public SimpleThreadLocalCache(int size, Supplier<T> supplier) {
      super(size, supplier);

      preAllocate();
   }
}
