package com.sirinath.utils.buffers.api.wrapped;

import com.sirinath.utils.buffers.api.accessors.GettableArrayBase;
import com.sirinath.utils.buffers.api.accessors.SettableArrayBase;

public interface WrappedArrayBufferBase<S extends WrappedArrayBufferBase<S, T>, T> extends WrappedBufferBase<S, T>, SettableArrayBase<T> {
    default S wrap(final T array) {
        setArray(array);
        return (S) this;
    }

    default S wrap(final GettableArrayBase<T> buffer) {
        return wrap(buffer.getArray());
    }
}
