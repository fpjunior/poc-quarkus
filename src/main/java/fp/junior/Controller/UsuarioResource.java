package fp.junior.Controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


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
}