package com.sirinath.utils.lowlevel.runners;

@FunctionalInterface
public interface ObjRunner<T> extends Runner {
    T run();
}
