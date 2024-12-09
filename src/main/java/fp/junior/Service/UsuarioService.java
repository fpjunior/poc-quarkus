package fp.junior.Service;

import java.util.List;

import fp.junior.Entity.Usuario;
import fp.junior.Repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UsuarioService {

    @Inject
    UsuarioRepository usuarioRepository;

    public Usuario buscarPorNome(String nome) {
        return usuarioRepository.findByName(nome);
    }

    public List<Usuario> buscarTodosUsuarios() {
        return usuarioRepository.findAllUsuarios();
    }

}