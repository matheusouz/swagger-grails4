package swagger.grails4.openapi.builder

import io.swagger.v3.oas.models.media.Schema
import swagger.grails4.openapi.builder.OpenApiAnnotationBuilder

class OpenApiSchemaBuilder implements OpenApiAnnotationBuilder<Schema> {
    Schema model = new Schema()

    @SuppressWarnings("unused")
    static Class openApiAnnotationClass = Schema

    def properties(Map<String, Closure> schemaMap) {
        if (!model.properties) {
            model.properties = [:]
        }
        schemaMap.each { name, classOrClosure ->
            def schema = buildSchema(classOrClosure)
            model.properties.put(name, schema)
        }
    }

    Schema buildSchema(Object classOrClosure) {
        if (classOrClosure instanceof Closure) {
            def builder = new OpenApiSchemaBuilder(reader: reader)
            return evaluateClosure(classOrClosure, builder)
        }else{
            return reader.buildSchema(classOrClosure as Class)
        }
    }
}
