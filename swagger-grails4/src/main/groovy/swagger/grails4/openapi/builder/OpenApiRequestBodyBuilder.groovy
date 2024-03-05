package swagger.grails4.openapi.builder

import io.swagger.v3.oas.models.media.Content
import io.swagger.v3.oas.models.parameters.RequestBody
import swagger.grails4.openapi.builder.OpenApiAnnotationBuilder
import swagger.grails4.openapi.builder.OpenApiMediaTypeBuilder

class OpenApiRequestBodyBuilder implements OpenApiAnnotationBuilder<RequestBody> {

    RequestBody model = new RequestBody()

    @SuppressWarnings("unused")
    static Class openApiAnnotationClass = io.swagger.v3.oas.annotations.parameters.RequestBody

    OpenApiRequestBodyBuilder(){
        initPrimitiveElements()
    }

    def content(Map<String, Closure> closureMap) {
        if (!model.content) {
            model.content = new Content()
        }
        closureMap.each { mime, closure ->
            OpenApiMediaTypeBuilder mediaTypeBuilder = new OpenApiMediaTypeBuilder(reader: reader)
            model.content.addMediaType(mime, evaluateClosure(closure, mediaTypeBuilder))
        }
    }
}
