package swagger.grails4


import main.swagger.grails4.samples.UserSaveDocumentDTO
import swagger.grails4.openapi.OpenApiDoc

@OpenApiDoc(tag = {
    description "User API???x"
})
class UserController {

    static namespace = "v3"
//
//    @OpenApiDoc(description = "Descrição", summary = "Sumário", responseType = UserCreateResponseDTO, examples = { UserCreateApiDoc.login })
//    public Map login(UserCreateRequestDTO command) {
//        return new UserCreateResponseDTO(username: "Novo usuário", password: "123456", keys: new UserCreateResponseKeyDTO(key: "1234")).properties
//    }
//
//    @OpenApiDoc(description = "Listar clientes", summary = "Lista")
//    public Map list(UserListRequestDTO command) {
//        return new UserCreateResponseDTO(username: "jose", password: "12345").properties
//    }
//
//    @OpenApiDoc(description = "Recuperar status do cliente")
//    public Map getStatus(String id) {
//        return [status: "OK"]
//    }
//
//    @OpenApiDoc(description = "Recuperar Doc")
//    public Map getDocument(String paymentId, String documentId) {
//        return [status: "OK"]
//    }

    @OpenApiDoc(description = "Salvar ou atualizar Doc")
    public Map saveDocument(UserSaveDocumentDTO userSaveDocumentDTO) {
        return [status: "OK"]
    }

}
