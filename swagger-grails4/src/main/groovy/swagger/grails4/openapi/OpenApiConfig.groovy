package swagger.grails4.openapi

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

@Retention(RetentionPolicy.RUNTIME)
@interface OpenApiConfig {
    Class value()
}
