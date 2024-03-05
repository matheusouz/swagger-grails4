package swagger.grails4.samples


import swagger.grails4.openapi.OpenApiDoc

@OpenApiDoc(description = "DTO para criação de usuário")
class UserCreateRequestDTO {

    @OpenApiDoc(required = true, description = "User name")
    String username

    @OpenApiDoc(maximum = 15, minimum = 5, required = true, description = "User password")
    String password

    @OpenApiDoc(description = "User test", defaultValue = { value MyEnum.PAYMENT_RECEIVED })
    MyEnum paymentType

    @OpenApiDoc(description = "User test", defaultValue = { value true })
    Boolean canCreateNew

    @OpenApiDoc(description = "User secret")
    UserCreateSecretDTO secret

    List<EmailDTO> emails

    @OpenApiDoc(description = "Arquivo", isFile = true)
    String file

}