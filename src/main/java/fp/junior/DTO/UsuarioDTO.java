package fp.junior.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import fp.junior.Entity.Usuario;
import lombok.AllArgsConstructor;

@Data 
@AllArgsConstructor 
@NoArgsConstructor 
public class UsuarioDTO {
    public String nome;
    private String email;
    private String senha;

    public UsuarioDTO(Usuario usuario) {
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
    }

}