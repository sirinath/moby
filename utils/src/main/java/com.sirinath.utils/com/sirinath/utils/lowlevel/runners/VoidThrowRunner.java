package com.sirinath.utils.lowlevel.runners;

@FunctionalInterface
public interface VoidThrowRunner extends ThrowRunner {
    void run() throws Throwable;
}
