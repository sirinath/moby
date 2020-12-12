package com.sirinath.utils.lowlevel.runners;

@FunctionalInterface
public interface ShortThrowRunner extends ThrowRunner {
    short run() throws Throwable;
}
