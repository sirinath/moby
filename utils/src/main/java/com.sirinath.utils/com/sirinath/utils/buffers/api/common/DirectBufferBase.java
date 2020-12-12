package com.sirinath.utils.buffers.api.common;

import com.sirinath.utils.buffers.api.accessors.DirectBufferAccessorBase;

public interface DirectBufferBase<S extends DirectBufferBase<S, T>, T> extends BufferBase<S, T>, DirectBufferAccessorBase<S, T> {
}
