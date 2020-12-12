package com.sirinath.utils.lowlevel;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;

public class UnsafeAccess {
    public static final Unsafe UNSAFE;
    public static final Unsafe $;

    static {
        Unsafe unsafe = null;

        try {
            final PrivilegedExceptionAction<Unsafe> action = () -> {
                final Field f = Unsafe.class.getDeclaredField("theUnsafe");
                f.setAccessible(true);

                return (Unsafe)f.get(null);
            };

            unsafe = AccessController.doPrivileged(action);
        } catch (final Exception ex) {
            Exceptions.Unchecked.throwUnchecked(ex);
        } finally {
            UNSAFE = unsafe;
            $ = unsafe;
        }
    }

    public static Unsafe getUnsafe() {
        return UNSAFE;
    }

    public static Unsafe $() {
        return $;
    }
}
