package swagger.grails4.samples

import swagger.grails4.openapi.OpenApiDoc

@OpenApiDoc("Page Command")
trait Page {
    /**
     * Page size
     */
    @OpenApiDoc("max")
    int max
    /**
     * Offset of records
     */
    @OpenApiDoc("offset")
    int offset
}
