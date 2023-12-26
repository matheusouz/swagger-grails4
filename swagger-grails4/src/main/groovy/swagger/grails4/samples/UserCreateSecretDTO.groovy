package swagger.grails4.samples


import swagger.grails4.openapi.OpenApiDoc

@OpenApiDoc("The command contains Secret properties")
class UserCreateSecretDTO {

    @OpenApiDoc(required = true, description = "its")
    String its

    @OpenApiDoc(maximum = 15, minimum = 5, required = true, description = "seecreet")
    String secret
}
