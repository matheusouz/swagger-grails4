package swagger.grails4.samples


import swagger.grails4.openapi.OpenApiDoc

@OpenApiDoc("The command contains Secret properties")
class EmailDTO {

    @OpenApiDoc(description = "Email do usuário")
    String email

    @OpenApiDoc(description = "Senha do usuário")
    String senha
}
