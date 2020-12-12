package com.sirinath.utils.lowlevel.runners;

@FunctionalInterface
public interface FloatThrowRunner extends ThrowRunner {
    float run() throws Throwable;
}
