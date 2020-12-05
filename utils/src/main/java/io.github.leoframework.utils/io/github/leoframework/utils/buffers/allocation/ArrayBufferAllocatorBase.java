package io.github.leoframework.utils.buffers.allocation;

public interface ArrayBufferAllocatorBase<T> extends BufferAllocatorBase {
    T allocate(final int elements);
}
