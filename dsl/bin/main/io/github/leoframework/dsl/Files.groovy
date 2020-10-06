package io.github.leoframework.dsl

import groovy.transform.CompileStatic
import groovy.transform.InheritConstructors

@CompileStatic
@InheritConstructors
class ProcessingUnit extends DSLElement {
}

@CompileStatic
@InheritConstructors
class TemplateFile extends ProcessingUnit {
}

@CompileStatic
@InheritConstructors
class ContentFile extends ProcessingUnit {
}

@CompileStatic
@InheritConstructors
class DataFile extends ProcessingUnit {
}