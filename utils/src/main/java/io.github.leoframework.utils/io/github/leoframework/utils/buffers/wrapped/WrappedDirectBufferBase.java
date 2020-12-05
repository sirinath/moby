package io.github.leoframework.utils.buffers.wrapped;

public interface WrappedDirectBufferBase<T extends WrappedDirectBufferBase<T>> extends WrappedBufferBase<T> {
    T wrap(final long address);
}
