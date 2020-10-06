package io.github.leoframework.dsl

import groovy.transform.CompileStatic
import groovy.transform.InheritConstructors
import groovy.transform.SelfType

@CompileStatic
@InheritConstructors
class Template extends DSLElement {
}

@CompileStatic
class TemplateFactory extends AbstractFactory {
    boolean isLeaf() {
        return false
    }

    Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes)
            throws InstantiationException, IllegalAccessException {
        return new Template(attributes)
    }
}

@SelfType(DSLFactoryBuilder)
@CompileStatic
trait TemplateRegistration {
    registerMeta() {
        registerFactory("template", new TemplateFactory())
    }
}