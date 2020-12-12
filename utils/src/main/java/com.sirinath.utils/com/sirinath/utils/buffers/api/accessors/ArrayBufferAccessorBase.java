package com.sirinath.utils.buffers.api.accessors;

public interface ArrayBufferAccessorBase<S extends ArrayBufferAccessorBase<S, T>, T> extends BufferAccessorBase<S, T>, GettableArrayBase<T> {
    default T getDataBuffer() {
        return getArray();
    }
}
