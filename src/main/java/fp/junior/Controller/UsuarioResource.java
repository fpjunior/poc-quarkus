package fp.junior.Controller;

import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.StringReader;
import java.util.List;
import java.util.stream.Collectors;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import fp.junior.DTO.UsuarioDTO;
import fp.junior.Entity.Usuario;
import fp.junior.Response.ApiResponseCustom;
import fp.junior.Service.UsuarioService;

@Path("/usuarios")
public class UsuarioResource {

    @Inject
    UsuarioService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ApiResponseCustom<List<UsuarioDTO>> getUsuarios() {
        try {
            List<UsuarioDTO> usuariosDTO = userService.findAllUsers();

            return new ApiResponseCustom<>("Usuários recuperados com sucesso", usuariosDTO, true);
        } catch (Exception e) {
            return new ApiResponseCustom<>("Erro ao recuperar usuários: " + e.getMessage(), null, false);
        }
    }

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Obter um usuário pelo nome", description = "Retorna um usuário com base no nome fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public ApiResponseCustom<UsuarioDTO> getUsuarioByName(@PathParam("name") String name) {
        try {
            UsuarioDTO usuario = userService.findByName(name);

            if (usuario != null) {
                return new ApiResponseCustom<>("Usuário encontrado com sucesso", usuario, true);
            } else {
                return new ApiResponseCustom<>("Usuário não encontrado", null, false);
            }

        } catch (Exception e) {
            return new ApiResponseCustom<>("Erro ao recuperar usuário: " + e.getMessage(), null, false);
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createLogin(UsuarioDTO userDTO) {
        try {
            Usuario usuarioCriado = userService.createUser(userDTO);

            UsuarioDTO usuarioDTOCriado = new UsuarioDTO(usuarioCriado);

            return Response.status(Response.Status.CREATED)
                    .entity(new ApiResponseCustom<>("Usuário criado com sucesso", usuarioDTOCriado, true))
                    .header("Location", "/usuarios/" + usuarioDTOCriado.getNome())
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ApiResponseCustom<>("Erro ao criar usuário: " + e.getMessage(), null, false))
                    .build();
        }
    }

    @POST
    @Path("/generate-login-by-procedure")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String loginGenerate(String emailJson) {
        String email = Json.createReader(new StringReader(emailJson)).readObject().getString("email");
        return userService.generateUserLogin(email);
    }
    
}