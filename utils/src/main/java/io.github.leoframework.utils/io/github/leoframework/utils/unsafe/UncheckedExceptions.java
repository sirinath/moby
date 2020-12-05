package io.github.leoframework.utils.unsafe;

import static io.github.leoframework.utils.unsafe.UnsafeAccess.UNSAFE;

public final class UncheckedExceptions {
    private UncheckedExceptions() {}

    public static void throwUnchecked(final Throwable ex) {
        UncheckedExceptions.<RuntimeException>throwUncheckedImplementation(ex);
    }

    public static void throwUncheckedUnsafe(final Throwable ex) {
        UNSAFE.throwException(ex);
    }

    @SuppressWarnings("unchecked")
    private static <T extends Throwable> void throwUncheckedImplementation(final Throwable t) throws T {
        throw (T)t;
    }
}
