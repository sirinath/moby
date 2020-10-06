package io.github.leoframework.dsl

import groovy.transform.CompileStatic
import groovy.transform.InheritConstructors
import groovy.transform.SelfType

@CompileStatic
@InheritConstructors
class Content extends DSLElement {
}

@CompileStatic
class ContentFactory extends AbstractFactory {
    boolean isLeaf() {
        return false
    }

    Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes)
            throws InstantiationException, IllegalAccessException {
        return new Content(attributes)
    }
}

@SelfType(DSLFactoryBuilder)
@CompileStatic
trait ContentRegistration {
    registerMeta() {
        registerFactory("content", new ContentFactory())
    }
}