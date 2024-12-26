package fp.junior.Repository;

import fp.junior.Entity.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<Usuario> {

    public List<Usuario> findAllUsers() {
        return findAll().list();
    }

    public Usuario findByName(String name) {
        return find("nome", name).firstResult();
    }

     public Usuario save(Usuario usuario) {
        persist(usuario); 
        return usuario;
    }
}
