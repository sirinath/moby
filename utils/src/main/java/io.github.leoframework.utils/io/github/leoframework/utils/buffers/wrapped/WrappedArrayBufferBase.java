package io.github.leoframework.utils.buffers.wrapped;

public interface WrappedArrayBufferBase<T extends WrappedArrayBufferBase<T, U>, U> extends WrappedBufferBase<T> {
    T wrap(final U array);
}
