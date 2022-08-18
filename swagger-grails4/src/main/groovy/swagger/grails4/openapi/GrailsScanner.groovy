package swagger.grails4.openapi

import grails.core.GrailsApplication
import grails.core.GrailsClass
import grails.core.GrailsControllerClass
import io.swagger.v3.oas.integration.api.OpenAPIConfiguration
import io.swagger.v3.oas.integration.api.OpenApiScanner

/**
 * Scanner for grails controllers
 *
 * @author bo.yang <bo.yang@telecwin.com>
 */
class GrailsScanner implements OpenApiScanner{
    GrailsApplication grailsApplication
    OpenAPIConfiguration openApiConfiguration
    String namespace

    @Override
    void setConfiguration(OpenAPIConfiguration openApiConfiguration) {
        this.openApiConfiguration = openApiConfiguration
    }

    @Override
    Set<Class<?>> classes() {
        def classes = []
        for (GrailsControllerClass cls in grailsApplication.controllerClasses) {
            Boolean isDefaultNamespace = !namespace && null == cls.getNamespace()
            Boolean isCustomNamespace = namespace && namespace == cls.getNamespace()
            if (isDefaultNamespace || isCustomNamespace) {
                classes << cls.clazz
            }
        }
        return classes
    }

    @Override
    Map<String, Object> resources() {
        return new HashMap<>();
    }
}
