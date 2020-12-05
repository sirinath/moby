package io.github.leoframework.utils.buffers.index;

public interface BufferIndexBase {
    long getIndexStart();

    long getIndexScale();

    private static long translateIndex(final long startIndex, final long scale, final long index) {
        return startIndex + scale * index;
    }

    default long translateIndex(final long index) {
        return translateIndex(getIndexStart(), getIndexScale(), index);
    }
}
