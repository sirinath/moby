package com.sirinath.utils.cache;

import com.conversantmedia.util.concurrent.DisruptorBlockingQueue;

import java.util.function.Supplier;

public final class SimpleCache<T> extends AbstractSimpleCache<T> {
    private final DisruptorBlockingQueue<T> queue;

    public SimpleCache(int size, Supplier<T> supplier) {
        super(size, supplier);

        queue = new DisruptorBlockingQueue<T>(size);

        preAllocate();
    }

    @Override
    protected final DisruptorBlockingQueue<T> getQueue() {
        return queue;
    }
}
