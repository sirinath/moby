package io.github.leoframework.dsl

import groovy.transform.CompileStatic
import org.eclipse.collections.impl.map.mutable.UnifiedMap

@CompileStatic
abstract class DSLElement extends UnifiedMap {
    DSLElement(Map attributes) {
        putAll(attributes)
    }

    getAt(Object key) {
        get(key)
    }

    putAt(Object key, Object value) {
        put(key, value)
    }
}

@CompileStatic
class BaseFactoryBuilder extends FactoryBuilderSupport {
    BaseFactoryBuilder(boolean init = true) {
        super(init)
    }
}

@CompileStatic
class DSLFactoryBuilder
        extends BaseFactoryBuilder
        implements MetaRegistration,
                ContentRegistration,
                TemplateRegistration {

}