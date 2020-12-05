package io.github.leoframework.utils.buffers.access;

public interface ArrayBufferAccessBase<T> extends BufferAccessBase {
    T getDataBuffer();
}
