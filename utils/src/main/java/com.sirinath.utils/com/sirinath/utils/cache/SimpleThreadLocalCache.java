package com.sirinath.utils.cache;

import com.conversantmedia.util.concurrent.DisruptorBlockingQueue;
import com.sirinath.utils.lowlevel.memory.Memory;

import java.util.function.Supplier;

public final class SimpleThreadLocalCache<T> extends AbstractSimpleCache<T> {
    private final ThreadLocal<DisruptorBlockingQueue<T>> threadLocalQueue = new ThreadLocal<DisruptorBlockingQueue<T>>() {
        @Override
        protected DisruptorBlockingQueue<T> initialValue() {
            return new DisruptorBlockingQueue<>(getSize());
        }
    };

    public SimpleThreadLocalCache(int size, Supplier<T> supplier) {
        super(size, supplier);

        preAllocate();
    }

    private Thread lastThread = Thread.currentThread();
    private DisruptorBlockingQueue<T> lastQueue = threadLocalQueue.get();

    @Override
    protected final DisruptorBlockingQueue<T> getQueue() {
        final Thread currentThread = Thread.currentThread();

        DisruptorBlockingQueue<T> lastQueue = this.lastQueue;
        final Thread lastThread = this.lastThread;
        Memory.acquireFence();

        if (currentThread != lastThread) {
            lastQueue = threadLocalQueue.get();

            this.lastThread = currentThread;
            this.lastQueue = lastQueue;
            Memory.releaseFence();
        }

        return lastQueue;
    }
}
