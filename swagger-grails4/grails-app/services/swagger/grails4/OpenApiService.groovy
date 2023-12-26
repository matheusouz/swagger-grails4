package swagger.grails4

import io.swagger.v3.oas.integration.GenericOpenApiContext
import io.swagger.v3.oas.integration.SwaggerConfiguration
import io.swagger.v3.oas.integration.api.OpenAPIConfiguration
import io.swagger.v3.oas.integration.api.OpenApiContext
import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import io.swagger.v3.oas.models.servers.Server
import org.codehaus.groovy.grails.commons.GrailsApplication
import swagger.grails4.openapi.OpenApiGrailsScanner
import swagger.grails4.openapi.OpenApiReader

class OpenApiService {

    GrailsApplication grailsApplication

    def generateDocument(String namespace) {
        OpenAPIConfiguration config = new SwaggerConfiguration().openAPI(configOpenApi(namespace))
        config.setReaderClass("swagger.grails4.openapi.OpenApiDocReader")
        OpenApiContext ctx = new GenericOpenApiContext().openApiConfiguration(config)
        ctx.setOpenApiScanner(new OpenApiGrailsScanner(grailsApplication: grailsApplication, namespace: namespace))
        ctx.setOpenApiReader(new OpenApiReader(application: grailsApplication, config: config))
        ctx.init()
        ctx.read()
    }

    OpenAPI configOpenApi(String namespace) {
        Map config = grailsApplication.config.openApi.doc."${namespace}"

        Info info = new Info().title(config.info.title).description(config.info.description).version(config.info.version)
        OpenAPI openAPI = new OpenAPI()
        openAPI.info(info)

        def serverConfig = config.servers
        if (serverConfig) {
            List<Server> servers = serverConfig.collect { serverMap -> new Server().url(serverMap?.url ?: null).description(serverMap?.description ?: null)}
            openAPI.servers(servers)
        }

        def securitySchemes = config.components?.securitySchemes
        securitySchemes?.each { name, map ->
            if (!openAPI.components) {
                openAPI.components(new Components())
            }
            def secScheme = new SecurityScheme(map)
            openAPI.components.addSecuritySchemes(name, secScheme)
        }

        def globalSecurity = config.security
        globalSecurity?.each { name, map ->
            if (!openAPI.security) {
                openAPI.security([])
            }

            openAPI.security.add(new SecurityRequirement().addList(name, map ?: []))
        }

        return openAPI
    }
}
