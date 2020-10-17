package io.github.leoframework.logging;

import io.github.leoframework.aop.Weave;

import java.lang.reflect.Method;
import java.time.Duration;
import java.time.Instant;

import net.bytebuddy.asm.Advice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogInjector extends Weave {
    @Override
    public Class<?> target() {
        return getClass();
    }

    private static Logger logger = LogManager.getRootLogger();

    @Advice.OnMethodEnter(suppress = Throwable.class)
    public static Instant onCall(@Advice.Enter Instant time, @Advice.Origin Method origin, @Advice.This Object self, @Advice.AllArguments Object[] arguments) {
        Instant now = null;
        Duration duration = null;
        try {
            now = Instant.now();
            duration = Duration.between(time, now);
        } finally {
            try {
                logger.trace("Duration since return: {}, Origin: {}, Object Identity Hash: {}, Arguments: {}", duration, origin, System.identityHashCode(self), Arrays.deepToString(arguments));
            } finally {
                return now;
            }
        }
    }

    @Advice.OnMethodExit(suppress = Throwable.class)
    public static Instant onReturn(@Advice.Enter Instant time, @Advice.Origin Method origin, @Advice.Return Object result) {
        Instant now = null;
        Duration duration = null;
        try {
            now = Instant.now();
            duration = Duration.between(time, now);
        } finally {
            try {
                logger.trace("Duration since call: {}, Origin: {}, Result: {}", duration, origin, result);
            } finally {
                return now;
            }
        }
    }

    @Advice.OnMethodExit(suppress = Throwable.class, onThrowable = Throwable.class)
    public static Instant onException(@Advice.Enter Instant time, @Advice.Origin Method origin, @Advice.Thrown Throwable thrown) {
        Instant now = null;
        Duration duration = null;
        try {
            now = Instant.now();
            duration = Duration.between(time, now);
        } finally {
            try {
                logger.trace("Duration since call: {}, Origin: {}, Thrown: {}", duration, origin, thrown);
            } finally {
                return now;
            }
        }
    }
}
