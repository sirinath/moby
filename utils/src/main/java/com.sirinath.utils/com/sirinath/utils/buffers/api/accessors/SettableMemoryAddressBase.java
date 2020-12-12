package com.sirinath.utils.buffers.api.accessors;

import com.sirinath.utils.lowlevel.memory.OffHeapMemory;

public interface SettableMemoryAddressBase<T> {
    void setAddress(final OffHeapMemory address);
}
