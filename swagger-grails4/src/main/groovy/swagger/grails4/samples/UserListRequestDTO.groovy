package swagger.grails4.samples


import swagger.grails4.openapi.OpenApiDoc

@OpenApiDoc(description = "DTO para listar usuários")
class UserListRequestDTO {

    @OpenApiDoc(description = "User name", required = true, maximum = 15)
    String name

    @OpenApiDoc(description = "max per page", maximum = 100, minimum = 1)
    int max

    @OpenApiDoc(description = "offset", minimum = 0)
    int offset
}
