package io.github.leoframework.utils.cache;

import com.conversantmedia.util.concurrent.DisruptorBlockingQueue;
import io.github.leoframework.utils.cache.AbstractSimpleCache;

import java.util.function.Supplier;

public final class SimpleThreadLocalCache<T> extends AbstractSimpleCache<T> {
    private final ThreadLocal<DisruptorBlockingQueue<T>> threadLocalQueue;

    public SimpleThreadLocalCache(int size, Supplier<T> supplier) {
        super(size, supplier);

        threadLocalQueue = new ThreadLocal<DisruptorBlockingQueue<T>>() {
            @Override
            protected DisruptorBlockingQueue<T> initialValue() {
                return new DisruptorBlockingQueue<>(size);
            }
        };

        preAllocate();
    }

    @Override
    protected final DisruptorBlockingQueue<T> getQueue() {
        return threadLocalQueue.get();
    }
}
