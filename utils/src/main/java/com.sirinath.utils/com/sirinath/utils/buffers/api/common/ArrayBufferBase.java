package com.sirinath.utils.buffers.api.common;

import com.sirinath.utils.buffers.api.accessors.ArrayBufferAccessorBase;

public interface ArrayBufferBase<S extends ArrayBufferBase<S, T>, T> extends BufferBase<S, T>, ArrayBufferAccessorBase<S, T> {
}