package com.sirinath.utils.lowlevel;

import com.sirinath.utils.lowlevel.runners.*;
import io.github.leoframework.utils.lowlevel.runners.*;

public final class Exceptions {
    private Exceptions() {}

    public static final class Unchecked {
        private Unchecked() {}

        public static void $(final Throwable ex) {
            throwUnchecked(ex);
        }

        public static void $(final VoidThrowRunner runner) {
            tryRun(runner);
        }

        public static void tryRun(final VoidThrowRunner runner) {
            try {
                runner.run();
            } catch (Throwable t) {
                $(t);
            }
        }

        public static <T> T $(final ObjThrowRunner<T> runner) {
            return tryRun(runner);
        }

        public static <T> T tryRun(final ObjThrowRunner<T> runner) {
            try {
                return runner.run();
            } catch (Throwable t) {
                $(t);
            }

            return null;
        }

        public static int $(final IntThrowRunner runner) {
            return tryRun(runner);
        }

        public static int tryRun(final IntThrowRunner runner) {
            try {
                return runner.run();
            } catch (Throwable t) {
                $(t);
            }

            return 0;
        }

        public static long $(final LongThrowRunner runner) {
            return tryRun(runner);
        }

        public static long tryRun(final LongThrowRunner runner) {
            try {
                return runner.run();
            } catch (Throwable t) {
                $(t);
            }

            return 0;
        }

        public static short $(final ShortThrowRunner runner) {
            return tryRun(runner);
        }

        public static short tryRun(final ShortThrowRunner runner) {
            try {
                return runner.run();
            } catch (Throwable t) {
                $(t);
            }

            return 0;
        }
        public static byte $(final ByteThrowRunner runner) {
            return tryRun(runner);
        }

        public static byte tryRun(final ByteThrowRunner runner) {
            try {
                return runner.run();
            } catch (Throwable t) {
                $(t);
            }

            return 0;
        }

        public static char $(final CharThrowRunner runner) {
            return tryRun(runner);
        }

        public static char tryRun(final CharThrowRunner runner) {
            try {
                return runner.run();
            } catch (Throwable t) {
                $(t);
            }

            return 0;
        }

        public static float $(final FloatThrowRunner runner) {
            return tryRun(runner);
        }

        public static float tryRun(final FloatThrowRunner runner) {
            try {
                return runner.run();
            } catch (Throwable t) {
                $(t);
            }

            return 0;
        }

        public static double $(final DoubleThrowRunner runner) {
            return tryRun(runner);
        }

        public static double tryRun(final DoubleThrowRunner runner) {
            try {
                return runner.run();
            } catch (Throwable t) {
                $(t);
            }

            return 0;
        }

        public static void throwUnchecked(final Throwable ex) {
            Unchecked.<RuntimeException>throwUncheckedImplementation(ex);
        }

        public static void throwUncheckedUnsafe(final Throwable ex) {
            UnsafeAccess.$.throwException(ex);
        }

        @SuppressWarnings("unchecked")
        private static <T extends Throwable> void throwUncheckedImplementation(final Throwable t) throws T {
            throw (T)t;
        }
    }
}
