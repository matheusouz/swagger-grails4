package main.swagger.grails4.openapi.builder

import main.swagger.grails4.openapi.OpenApiDocExampleValue
import swagger.grails4.openapi.builder.OpenApiAnnotationBuilder

class OpenApiDocExampleValueBuilder implements OpenApiAnnotationBuilder<OpenApiDocExampleValue> {

    OpenApiDocExampleValue model = new OpenApiDocExampleValue()

    @SuppressWarnings("unused")
    static Class openApiAnnotationClass = OpenApiDocExampleValue

    OpenApiDocExampleValueBuilder(){
        initPrimitiveElements()
    }

    def request(Object value) {
        model.request = value
    }

    def responses(Object value) {
        model.responses = value
    }
}
