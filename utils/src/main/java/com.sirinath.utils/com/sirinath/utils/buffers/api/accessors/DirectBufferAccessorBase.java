package com.sirinath.utils.buffers.api.accessors;

public interface DirectBufferAccessorBase<S extends DirectBufferAccessorBase<S, T>, T> extends BufferAccessorBase<S, T>, GettableMemoryAddressBase<T> {
    long getMemoryAddress();

    default long getDataBuffer() {
        return getMemoryAddress();
    }
}
