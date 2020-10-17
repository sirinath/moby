package io.github.leoframework.aop;

import net.bytebuddy.asm.Advice;
import net.bytebuddy.description.modifier.ModifierContributor;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.build.Plugin;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;

public abstract class Weave implements Plugin {
    public abstract Class<?> target();

    @Override
    public boolean matches(TypeDescription target) {
        return !target.isAssignableTo(this.getClass());
    }

    @Override
    public DynamicType.Builder<?> apply(
            DynamicType.Builder<?> builder,
            TypeDescription typeDescription,
            ClassFileLocator classFileLocator
    ) {
        return builder
                .method(ElementMatchers.any())
                .intercept(Advice.to(target()));
    }

    @Override
    public void close() { }
}