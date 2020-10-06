import groovy.transform.CompileStatic
import io.github.leoframework.dsl.DSLElement
import org.eclipse.collections.api.map.ImmutableMap

@Singleton
@CompileStatic
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
