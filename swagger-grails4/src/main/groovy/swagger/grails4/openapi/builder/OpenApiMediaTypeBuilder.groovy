package swagger.grails4.openapi.builder

import io.swagger.v3.oas.models.media.MediaType
import swagger.grails4.openapi.builder.OpenApiAnnotationBuilder
import swagger.grails4.openapi.builder.OpenApiSchemaBuilder

class OpenApiMediaTypeBuilder implements OpenApiAnnotationBuilder<MediaType> {
    MediaType model = new MediaType()

    @SuppressWarnings("unused")
    static Class openApiAnnotationClass = MediaType

    def schema(Map options, classOrClosure) {
        OpenApiSchemaBuilder builder = new OpenApiSchemaBuilder(reader: reader)
        model.schema = builder.buildSchema(classOrClosure)

        if (options && options["properties"]) {
            model.schema.$ref = null
            options["properties"].each { propName, propDefinition ->
                def propSchemaBuilder = new OpenApiSchemaBuilder(reader: reader)
                def schema = propSchemaBuilder.buildSchema(propDefinition)
                this.model.schema.properties.put(propName, schema)
            }
        }
    }

    def schema(classOrClosure) {
        schema([:], classOrClosure)
    }

    def schema(classOrClosure, Map options) {
        schema(options, classOrClosure)
    }
}
