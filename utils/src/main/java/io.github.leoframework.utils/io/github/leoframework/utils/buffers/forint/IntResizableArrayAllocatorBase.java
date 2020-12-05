package io.github.leoframework.utils.buffers.forint;

import io.github.leoframework.utils.buffers.resizable.ResizableArrayBufferBase;

import java.util.Arrays;

public interface IntResizableArrayAllocatorBase extends ResizableArrayBufferBase<int[]> {
    @Override
    default int[] reallocate(final int elements) {
        return Arrays.copyOf(getDataBuffer(), elements);
    }
}
