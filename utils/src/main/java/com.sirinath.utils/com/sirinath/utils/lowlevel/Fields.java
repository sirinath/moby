package com.sirinath.utils.lowlevel;

import java.lang.reflect.Field;

public final class Fields {
    private Fields() {}

    public static <T> Field getField(final Class<T> c, final String field) {
        try {
            return c.getField(field);
        } catch (NoSuchFieldException e) {
            Exceptions.Unchecked.$(e);
        }
        return null;
    }

    public static <T> Field $(final Class<T> c, final String field) {
        return getField(c, field);
    }

    public static final class Offset {
        private Offset() {}

        public static <T> long getFieldOffset(final Field f) {
            return UnsafeAccess.$.objectFieldOffset(f);
        }

        public static <T> long $(final Field f) {
            return Offset.getFieldOffset(f);
        }

        public static <T> long getFieldOffset(final Class<T> c, final String field) {
            final Field f = Fields.$(c, field);
            return getFieldOffset(f);
        }

        public static <T> long $(final Class<T> c, final String field) {
            return getFieldOffset(c, field);
        }
    }
}
