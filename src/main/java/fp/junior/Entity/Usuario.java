package fp.junior.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Usuario {
    @Id
    public Long id;
    public String nome;
    public String email;
}