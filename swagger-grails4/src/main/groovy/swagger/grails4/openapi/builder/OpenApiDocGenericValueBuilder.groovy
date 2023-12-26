package main.swagger.grails4.openapi.builder

import main.swagger.grails4.openapi.OpenApiDocGenericValue
import swagger.grails4.openapi.builder.OpenApiAnnotationBuilder

class OpenApiDocGenericValueBuilder implements OpenApiAnnotationBuilder<OpenApiDocGenericValue> {

    OpenApiDocGenericValue model = new OpenApiDocGenericValue()

    @SuppressWarnings("unused")
    static Class openApiAnnotationClass = OpenApiDocGenericValue

    OpenApiDocGenericValueBuilder(){
        initPrimitiveElements()
    }

    def value(Object value) {
        model.value = value
    }
}
