package io.github.leoframework.utils.buffers.forint;

import io.github.leoframework.utils.buffers.allocation.ArrayBufferAllocatorBase;

public interface IntArrayBufferAllocatorBase extends ArrayBufferAllocatorBase<int[]> {
    @Override
    default int[] allocate(final int elements) {
        return new int[elements];
    }
}
