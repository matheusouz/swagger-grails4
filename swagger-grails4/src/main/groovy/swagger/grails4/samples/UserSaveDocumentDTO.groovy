package main.swagger.grails4.samples

import swagger.grails4.openapi.OpenApiDoc

@OpenApiDoc(description = "DTO para salvar documentos")
class UserSaveDocumentDTO {

    @OpenApiDoc(required = true, description = "Id da cobrança", inPath = true)
    String paymentId

    @OpenApiDoc(required = true, description = "Id do documento", inPath = true)
    String documentId

    @OpenApiDoc(description = "Nome do documento")
    String documentName

    @OpenApiDoc(description = "Tipo do documento")
    String documentType

}
