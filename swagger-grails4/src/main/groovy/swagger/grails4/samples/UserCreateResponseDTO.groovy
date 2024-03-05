package swagger.grails4.samples


import swagger.grails4.openapi.OpenApiDoc

@OpenApiDoc("The command contains User properties")
class UserCreateResponseDTO {

    String username

    String password

    List<UserCreateResponseKeyDTO> keys

    public UserCreateResponseDTO() {
    }
}
