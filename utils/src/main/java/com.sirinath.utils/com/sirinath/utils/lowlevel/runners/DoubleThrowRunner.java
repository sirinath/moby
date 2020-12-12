package com.sirinath.utils.lowlevel.runners;

@FunctionalInterface
public interface DoubleThrowRunner extends ThrowRunner {
    double run() throws Throwable;
}
