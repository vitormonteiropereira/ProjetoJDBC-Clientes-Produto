import static org.junit.jupiter.api.Assertions.*;

import org.example.dao.ClienteDAO;
import org.example.dao.IDAO;
import org.example.domain.Cliente;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ClienteDAOTest {

    private IDAO<Cliente> dao;
    private Cliente cliente;
    private Cliente cliente2;

    @BeforeEach
    public void setUp() throws Exception {
        dao = new ClienteDAO();
        cliente = new Cliente();
        cliente.setCodigo("10");
        cliente.setNome("Vitor");
        dao.cadastrar(cliente);
    }

    @AfterEach
    public void tearDown() throws Exception {
        List<Cliente> clientes = dao.retornaTodos();
        for (Cliente cliente : clientes) {
            dao.excluir(cliente.getCodigo());
        }
    }

    @Test
    public void deveCadastrarCliente() throws Exception {
        Cliente clienteBD = dao.consultar(cliente.getCodigo());
        assertNotNull(clienteBD);
        assertNotNull(clienteBD.getId());
        assertEquals("Vitor", clienteBD.getNome());
        assertEquals("10", clienteBD.getCodigo());
    }

    @Test
    public void deveAtualizarCliente() throws Exception {
        Cliente clienteBD = dao.consultar(cliente.getCodigo());
        assertNotNull(clienteBD);
        assertNotNull(clienteBD.getId());
        assertEquals(clienteBD.getNome(), cliente.getNome());
        assertEquals(clienteBD.getCodigo(), cliente.getCodigo());

        clienteBD.setNome("Carolina");
        clienteBD.setCodigo("99");
        Integer qtdUpdate = dao.atualizar(clienteBD);
        assertTrue(qtdUpdate == 1);

        Cliente clienteBD2 = dao.consultar(clienteBD.getCodigo());
        assertNotNull(clienteBD2);
        Cliente clienteBD3 = dao.consultar("10");
        assertNull(clienteBD3);
        assertEquals("99", clienteBD.getCodigo());
        assertEquals("Carolina", clienteBD.getNome());
    }

    @Test
    public void deveExcluirCliente() throws Exception {
        Integer qtdDelete = dao.excluir(cliente.getCodigo());
        assertTrue(qtdDelete == 1);

        Cliente clienteBD = dao.consultar(cliente.getCodigo());
        assertNull(clienteBD);
    }


    @Test
    public void deveRetornarTodos () throws Exception {
        cliente2 = new Cliente();
        cliente2.setNome("Luis");
        cliente2.setCodigo("57");
        dao.cadastrar(cliente2);
        List lista = dao.retornaTodos();
        assertNotNull(lista);
        assertTrue(lista.size() == 2);


    }


}
