package io.github.leoframework.utils.unsafe;

public final class UncheckedExceptions {
    private UncheckedExceptions() {}

    public static void rethrowAsUnchecked(final Throwable ex) {
        UncheckedExceptions.<RuntimeException>rethrow(ex);
    }

    public static void rethrowAsUnchecked(final Throwable ex) {
        UNSAFE
    }

    @SuppressWarnings("unchecked")
    private static <T extends Throwable> void rethrow(final Throwable t) throws T {
        throw (T)t;
    }
}
