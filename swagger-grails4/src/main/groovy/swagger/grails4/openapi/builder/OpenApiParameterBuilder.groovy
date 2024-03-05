package swagger.grails4.openapi.builder

import io.swagger.v3.oas.models.parameters.Parameter
import swagger.grails4.openapi.builder.OpenApiAnnotationBuilder
import swagger.grails4.openapi.builder.OpenApiSchemaBuilder

class OpenApiParameterBuilder implements OpenApiAnnotationBuilder<Parameter> {
    Parameter model = new Parameter()

    @SuppressWarnings("unused")
    static Class openApiAnnotationClass = io.swagger.v3.oas.annotations.Parameter

    OpenApiParameterBuilder(){
        initPrimitiveElements()
    }

    def inType(String inType) {
        model.in(inType)
    }

    def schema(classOrClosure) {
        OpenApiSchemaBuilder builder = new OpenApiSchemaBuilder(reader: reader)
        model.schema = builder.buildSchema(classOrClosure)
    }
}
