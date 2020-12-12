package com.sirinath.utils.buffers.api.wrapped;

import com.sirinath.utils.buffers.api.accessors.GettableMemoryAddressBase;
import com.sirinath.utils.buffers.api.accessors.SettableMemoryAddressBase;
import com.sirinath.utils.lowlevel.memory.OffHeapMemory;

public interface WrappedDirectBufferBase<S extends WrappedDirectBufferBase<S, T>, T> extends WrappedBufferBase<S, T>, SettableMemoryAddressBase<T> {
    default S wrap(final OffHeapMemory address) {
        setAddress(address);
        return (S) this;
    }

    default S wrap(final GettableMemoryAddressBase<T> buffer) {
        return wrap(buffer.getAddress());
    }
}
