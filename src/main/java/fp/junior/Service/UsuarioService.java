package fp.junior.Service;

import java.util.List;

import fp.junior.DTO.UsuarioDTO;
import fp.junior.Entity.Usuario;
import fp.junior.Repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UsuarioService {

    @Inject
    UsuarioRepository userRepository;

    @Inject 
    EntityManager em;

    public UsuarioDTO findByName(String name) {
        Usuario usuario = userRepository.findByName(name);
        
        if (usuario != null) {
            return new UsuarioDTO(usuario);
        }
        return null;
    }

    public List<UsuarioDTO> findAllUsers() {
        List<Usuario> usuarios = userRepository.findAllUsers();
        
        // Mapeia a lista de entidades Usuario para UsuarioDTO
        return usuarios.stream()
                       .map(usuario -> new UsuarioDTO(usuario))
                       .toList(); // Retorna a lista de DTOs
    }
    @Transactional
    public Usuario createUser(UsuarioDTO usuarioDTO) {
        // Validação básica
        if (usuarioDTO.getNome() == null || usuarioDTO.getNome().isEmpty()) {
            throw new IllegalArgumentException("Nome do usuário é obrigatório");
        }
    
        // Criando a entidade Usuario a partir do DTO
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setSenha(usuarioDTO.getSenha());
    
        usuario.persist(); // Persistindo a entidade no banco de dados
        return usuario; // Retornando a entidade para ser convertida em DTO no controller
    }
    

     public String generateUserLogin(String email) {
        var query = em.createStoredProcedureQuery("cdit.criar_login_usuario");
        query.registerStoredProcedureParameter("email", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("result", String.class, ParameterMode.OUT);

        query.setParameter("email", email);
        query.execute();

        return (String) query.getOutputParameterValue("result");
    }

}