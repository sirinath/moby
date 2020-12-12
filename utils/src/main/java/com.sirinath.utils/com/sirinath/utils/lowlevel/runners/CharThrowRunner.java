package com.sirinath.utils.lowlevel.runners;

@FunctionalInterface
public interface CharThrowRunner extends ThrowRunner {
    char run() throws Throwable;
}
