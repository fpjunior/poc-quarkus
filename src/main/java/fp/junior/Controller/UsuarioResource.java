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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import fp.junior.DTO.UsuarioDTO;
import fp.junior.Entity.Usuario;
import fp.junior.Repository.UsuarioRepository;
import fp.junior.Service.UsuarioService;

@Path("/usuarios")
public class UsuarioResource {

    @Inject
    UsuarioService usuarioService;
    // Endpoint para retornar todos os usuários
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> getUsuarios() {
        return usuarioService.buscarTodosUsuarios();
    }

    // Endpoint para retornar um usuário específico pelo nome
    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Obter um usuário pelo nome", description = "Retorna um usuário com base no nome fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public Response getUsuarioByName(@PathParam("name") String name) {
        Usuario usuario = usuarioService.buscarPorNome(name);
        if (usuario == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Usuário não encontrado")
                    .build();
        }
        return Response.ok(usuario).build();
    }

    @POST
    public Response criarUsuario(UsuarioDTO usuarioDTO) {
        // Chama o serviço para criar o novo usuário
        Usuario usuarioCriado = usuarioService.criarUsuario(usuarioDTO);

        // Retorna o usuário criado com um status HTTP 201 (Created)
        return Response.status(Response.Status.CREATED)
                .entity(usuarioCriado)
                .build();
    }

    @POST
    @Path("/gerar-login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String gerarLogin(String emailJson) {
        // Supondo que o JSON enviado é {"email": "joao.silva@example.com"}
        String email = Json.createReader(new StringReader(emailJson)).readObject().getString("email");
        return usuarioService.gerarLoginUsuario(email);
    }
}