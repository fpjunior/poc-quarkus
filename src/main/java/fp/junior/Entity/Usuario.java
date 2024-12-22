package fp.junior.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data // Gera getters, setters, equals, hashCode e toString
@Table(name = "usuario") 
@AllArgsConstructor // Gera um construtor com todos os argumentos
@NoArgsConstructor // Gera um construtor sem argumentos
@Builder
public class Usuario extends PanacheEntityBase {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String nome;
    public String email;
    public String senha;
}