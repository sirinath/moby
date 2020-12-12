package com.sirinath.utils.buffers.api.accessors;

import com.sirinath.utils.lowlevel.memory.TypedOffHeapMemory;

public interface GettableMemoryAddressBase<T> {
    TypedOffHeapMemory<T> getAddress();
}
