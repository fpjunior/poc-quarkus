package fp.junior.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import fp.junior.Entity.Usuario;
import lombok.AllArgsConstructor;

@Data // Gera getters, setters, equals, hashCode e toString
@AllArgsConstructor // Gera um construtor com todos os argumentos
@NoArgsConstructor // Gera um construtor sem argumentos
public class UsuarioDTO {
    public String nome;
    private String email;
    private String senha;

     // Construtor que aceita a entidade Usuario
    public UsuarioDTO(Usuario usuario) {
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.email = usuario.getSenha();
    }
}