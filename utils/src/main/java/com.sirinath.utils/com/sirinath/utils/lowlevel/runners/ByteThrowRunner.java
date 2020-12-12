package com.sirinath.utils.lowlevel.runners;

@FunctionalInterface
public interface ByteThrowRunner extends ThrowRunner {
    byte run() throws Throwable;
}
