package com.sirinath.utils;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeAccess {
   public final static Unsafe UNSAFE;

   static {
      Unsafe theUnsafe;
      try {
         Field f = Unsafe.class.getDeclaredField("theUnsafe");
         f.setAccessible(true);
         theUnsafe = (Unsafe) f.get(null);
      } catch (Throwable t) {
         theUnsafe = null;
         UnsafeAccess.<RuntimeException>rethrow(t);
      }

      UNSAFE = theUnsafe;
   }

   @SuppressWarnings("unchecked")
   public static <T extends Throwable> void rethrow(final Throwable t) throws T {
      throw (T) t;
   }

   public static long getFieldOffset(final String field, final Class<?> klass) {
      try {
         return UNSAFE.objectFieldOffset(klass.getField(field));
      } catch (NoSuchFieldException e) {
         UnsafeAccess.rethrow(e);
         return -1;
      }
   }
}
