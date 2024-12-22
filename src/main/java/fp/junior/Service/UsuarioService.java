package fp.junior.Service;

import java.util.List;

import fp.junior.DTO.UsuarioDTO;
import fp.junior.Entity.Usuario;
import fp.junior.Repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UsuarioService {

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    EntityManager em;

    public Usuario buscarPorNome(String nome) {
        return usuarioRepository.findByName(nome);
    }

    public List<Usuario> buscarTodosUsuarios() {
        return usuarioRepository.findAllUsuarios();
    }

    @Transactional
    public UsuarioDTO adicionarUsuario(UsuarioDTO usuarioDTO) {
        // Validação básica
        if (usuarioDTO.getNome() == null || usuarioDTO.getNome().isEmpty()) {
            throw new IllegalArgumentException("Nome do usuário é obrigatório");
        }

        // Transformar DTO em entidade e salvar no banco
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setSenha(usuarioDTO.getSenha());

        usuario.persist(); // Usando Panache para persistir no banco

        // Retornar o DTO do usuário criado
        return new UsuarioDTO(usuario);
    }

    @Transactional
    public Usuario criarUsuario(UsuarioDTO usuarioDTO) {
        // Criar uma nova instância de Usuario a partir do DTO
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setSenha(usuarioDTO.getSenha());
        
        // Salvar no banco de dados
        return usuarioRepository.save(usuario);
    }

}