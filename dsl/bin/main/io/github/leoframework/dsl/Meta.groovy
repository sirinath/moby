package io.github.leoframework.dsl

import groovy.transform.CompileStatic
import groovy.transform.InheritConstructors
import groovy.transform.SelfType

@CompileStatic
@InheritConstructors
class Meta extends DSLElement {}

@CompileStatic
class MetaFactory extends AbstractFactory {
    boolean isLeaf() {
        return false
    }

    Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes)
            throws InstantiationException, IllegalAccessException {
        return new Meta(attributes)
    }
}

@SelfType(DSLFactoryBuilder)
@CompileStatic
trait MetaRegistration {
    registerMeta() {
        registerFactory("meta", new MetaFactory())
    }
}