package com.sirinath.utils.lowlevel.runners;

@FunctionalInterface
public interface IntThrowRunner extends ThrowRunner {
    int run() throws Throwable;
}
