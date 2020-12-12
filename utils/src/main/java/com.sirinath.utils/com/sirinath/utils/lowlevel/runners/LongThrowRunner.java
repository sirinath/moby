package com.sirinath.utils.lowlevel.runners;

@FunctionalInterface
public interface LongThrowRunner extends ThrowRunner {
    long run() throws Throwable;
}
