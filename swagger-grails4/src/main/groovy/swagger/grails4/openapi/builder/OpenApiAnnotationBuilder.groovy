package swagger.grails4.openapi.builder

import swagger.grails4.openapi.OpenApiReader

import java.lang.annotation.Annotation
import java.lang.reflect.Method

trait OpenApiAnnotationBuilder<T> {

    T model

    OpenApiReader reader

    private List systemMethods = ["equals", "toString", "hashCode", "annotationType"]

    private Set primitiveElements = []

    def initPrimitiveElements() {
        if (Annotation.isAssignableFrom(openApiAnnotationClass)) {
            initAnnotationPrimitiveElements()
        }else{
            initClassPrimitiveProperties()
        }
    }

    def initClassPrimitiveProperties(){
        openApiAnnotationClass.metaClass.properties.each { MetaProperty metaProperty ->
            if (isPrimitiveElement(metaProperty.type)){
                primitiveElements << metaProperty.name
            }
        }
    }

    def initAnnotationPrimitiveElements() {
        openApiAnnotationClass.methods.each { Method method ->
            if (method.name in systemMethods) {
                return
            }
            def elementType = method.returnType
            String propertyName = method.name
            if (isPrimitiveElement(elementType)) {
                if (getModel().hasProperty(propertyName)) {
                    if (!isPrimitiveNotNullableElement(elementType)) {
                        getModel()[propertyName] = method.defaultValue
                    }
                    primitiveElements << propertyName
                }
            }
        }
    }

    def isPrimitiveElement(elementType) {
        switch (elementType) {
            case String:
            case String[]:
            case Number:
            case Number[]:
            case Boolean:
            case Boolean[]:
                return true
        }
        return isPrimitiveNotNullableElement(elementType)
    }

    def isPrimitiveNotNullableElement(elementType) {
        switch (elementType) {
            case boolean:
                return true
        }
        return false
    }

    def methodMissing(String name, Object args) {
        if (!primitiveElements) {
            initPrimitiveElements()
        }

        if (name in primitiveElements) {
            getModel()[name] = args[0]
        }
    }

    def <M> M evaluateClosure(Closure closure, OpenApiAnnotationBuilder<M> builder) {
        def builderClosure = closure.rehydrate(builder, this, this)
        builderClosure.resolveStrategy = Closure.DELEGATE_ONLY
        builderClosure()
        builder.model
    }
}
