package io.github.leoframework.gen

import groovy.transform.CompileStatic
import groovy.transform.InheritConstructors
import io.github.leoframework.dsl.DSLElement
import org.eclipse.collections.api.map.ImmutableMap

@Singleton
@CompileStatic
@InheritConstructors
class LeoInternalCollections extends DSLElement {
    build() {
        synchronized (this) {

            LeoCollections.instance.data = LeoInternalCollections.instance.toImmutable()
        }
    }
}

@Singleton
@CompileStatic
class LeoCollections {
    @Delegate ImmutableMap data = LeoInternalCollections.instance.toImmutable()
}
