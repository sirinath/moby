package com.sirinath.utils.cache;

import com.conversantmedia.util.concurrent.DisruptorBlockingQueue;

import java.util.function.Supplier;

public abstract class AbstractSimpleCache<T> {
    private final int size;
    private final Supplier<T> supplier;

    protected abstract DisruptorBlockingQueue<T> getQueue();

    protected final void preAllocate() {
        final DisruptorBlockingQueue<T> queue = getQueue();
        for(int i = 0; i < size; i++)
            queue.offer(supplier.get());
    }

    public AbstractSimpleCache(final int size, final Supplier<T> supplier) {
        this.size = size;
        this.supplier = supplier;
    }

    public final T get() {
        final DisruptorBlockingQueue<T> queue = getQueue();
        if (queue.isEmpty()) {
            for(int i = 0; i < size; i++)
                queue.offer(supplier.get());
        }

        return queue.remove();
    }

    public final boolean put(final T value) {
        final DisruptorBlockingQueue<T> queue = getQueue();
        return queue.offer(value);
    }

    public final int getSize() {
        return size;
    }
}
