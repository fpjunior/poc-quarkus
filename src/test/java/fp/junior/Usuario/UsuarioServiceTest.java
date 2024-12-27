package fp.junior.Usuario;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import fp.junior.Repository.UsuarioRepository;
import fp.junior.Service.UsuarioService;
import fp.junior.DTO.UsuarioDTO;
import fp.junior.Entity.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class) // Garante a inicialização dos mocks
class UsuarioServiceTest {

    @Mock
    UsuarioRepository userRepository;

    @InjectMocks
    UsuarioService usuarioService; // O serviço com dependências simuladas será injetado

    /**
     * esse bloco de teste está verificando:
     * 
     * O comportamento do método findAllUsers() do serviço.
     * Que ele interage corretamente com o repositório.
     * Que ele converte os dados corretamente de Usuario para UsuarioDTO.
     */
    @Test
    void testFindAllUsuarios() {
        // Lista simulada de usuários
        List<Usuario> usuarios = List.of(
                Usuario.builder().id(1L).nome("João").email("joao@example.com").senha("senha123").build(),
                Usuario.builder().id(2L).nome("Maria").email("maria@example.com").senha("senha456").build());

        // Mock do PanacheQuery
        PanacheQuery<Usuario> mockQuery = Mockito.mock(PanacheQuery.class);

        // Configura o comportamento do mock
        when(mockQuery.list()).thenReturn(usuarios); // Retorna a lista de usuários
        when(userRepository.findAll()).thenReturn(mockQuery); // Retorna o mock do PanacheQuery

        // Chama o método do serviço
        List<UsuarioDTO> result = usuarioService.findAllUsers();

        // Verifica o resultado
        assertEquals(2, result.size(), "O número de usuários deve ser 2");
        assertEquals("João", result.get(0).getNome(), "O primeiro nome deve ser João");
        assertEquals("joao@example.com", result.get(0).getEmail(), "O primeiro email deve ser joao@example.com");
        assertEquals("Maria", result.get(1).getNome(), "O segundo nome deve ser Maria");
        assertEquals("maria@example.com", result.get(1).getEmail(), "O segundo email deve ser maria@example.com");
    }
}
