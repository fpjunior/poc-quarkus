package fp.junior.Repository;

import fp.junior.Entity.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<Usuario> {

    // Método para encontrar todos os usuários
    public List<Usuario> findAllUsuarios() {
        return findAll().list();
    }

    // Método para encontrar um usuário pelo nome
    public Usuario findByName(String name) {
        return find("nome", name).firstResult();
    }
}
