package swagger.grails4.openapi.builder

import io.swagger.v3.oas.models.Operation
import io.swagger.v3.oas.models.parameters.RequestBody
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.tags.Tag
import swagger.grails4.openapi.builder.OpenApiAnnotationBuilder
import swagger.grails4.openapi.builder.OpenApiParameterBuilder
import swagger.grails4.openapi.builder.OpenApiRequestBodyBuilder
import swagger.grails4.openapi.builder.OpenApiResponseBuilder
import swagger.grails4.openapi.builder.OpenApiTagBuilder

class OpenApiOperationBuilder implements OpenApiAnnotationBuilder<Operation> {

    Operation model = new Operation()

    @SuppressWarnings("unused")
    static Class openApiAnnotationClass = io.swagger.v3.oas.annotations.Operation

    OpenApiOperationBuilder(){
        initPrimitiveElements()
    }

    def parameters(List<Closure> parameterClosures) {
        if (!model.parameters) {
            model.parameters = []
        }
        parameterClosures.each { closure ->
            OpenApiParameterBuilder builder = new OpenApiParameterBuilder(reader: reader)
            model.parameters << evaluateClosure(closure, builder)
        }
    }

    def tags(List<Closure> tagClosures) {
        if (!model.tags) {
            model.tags = []
        }
        tagClosures.each { closure ->
            OpenApiTagBuilder builder = new OpenApiTagBuilder(reader: reader)
            Tag t = evaluateClosure(closure, builder)
            model.tags << t.name
        }
    }

    def security(Map<String, List<String>> securityMap) {
        if (!model.security) {
            model.security = []
        }

        securityMap.each { name, entries ->
            def secReq = new SecurityRequirement()
            secReq.addList(name, entries)
            model.security << secReq
        }
    }

    def requestBody(Closure requestBodyClosure) {
        if (!requestBodyClosure) {
            return
        }

        if (!model.requestBody) {
            RequestBody body = evaluateClosure(requestBodyClosure, new OpenApiRequestBodyBuilder(reader: reader))
            if (body) {
                model.requestBody = body
            }
        }
    }

    def responses(Map<String, Closure> responsesClosures) {
        if (!model.responses) {
            model.responses = []
        }
        responsesClosures.each { code, closure ->
            OpenApiAnnotationBuilder builder = new OpenApiResponseBuilder(reader: reader)
            def resp = evaluateClosure(closure, builder)
            model.responses.put(code, resp)
        }
    }
}
