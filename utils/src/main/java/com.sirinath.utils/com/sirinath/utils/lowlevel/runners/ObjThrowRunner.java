package com.sirinath.utils.lowlevel.runners;

@FunctionalInterface
public interface ObjThrowRunner<T> extends ThrowRunner {
    T run() throws Throwable;
}
