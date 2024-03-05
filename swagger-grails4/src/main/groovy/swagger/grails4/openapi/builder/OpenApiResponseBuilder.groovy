package swagger.grails4.openapi.builder

import io.swagger.v3.oas.models.media.Content
import io.swagger.v3.oas.models.responses.ApiResponse
import swagger.grails4.openapi.builder.OpenApiAnnotationBuilder
import swagger.grails4.openapi.builder.OpenApiMediaTypeBuilder

class OpenApiResponseBuilder implements OpenApiAnnotationBuilder<ApiResponse> {
    ApiResponse model = new ApiResponse()

    @SuppressWarnings("unused")
    static Class openApiAnnotationClass = io.swagger.v3.oas.annotations.responses.ApiResponse

    OpenApiResponseBuilder() {
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
