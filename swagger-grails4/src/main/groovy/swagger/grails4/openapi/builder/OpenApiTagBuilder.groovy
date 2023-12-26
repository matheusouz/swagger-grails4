package swagger.grails4.openapi.builder

import io.swagger.v3.oas.models.tags.Tag
import swagger.grails4.openapi.builder.OpenApiAnnotationBuilder

class OpenApiTagBuilder implements OpenApiAnnotationBuilder<Tag> {
    Tag model = new Tag()

    @SuppressWarnings("unused")
    static Class openApiAnnotationClass = io.swagger.v3.oas.annotations.tags.Tag

    OpenApiTagBuilder(){
        initPrimitiveElements()
    }
}
