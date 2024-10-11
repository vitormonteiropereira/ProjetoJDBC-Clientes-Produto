import static org.junit.jupiter.api.Assertions.*;

import org.example.dao.IDAO;
import org.example.dao.ProdutoDAO;
import org.example.domain.Produto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ProdutoDAOTest {

    private IDAO<Produto> dao;
    private Produto produto;
    private Produto produto2;

    @BeforeEach
    public void setUp() throws Exception {
        dao = new ProdutoDAO();
        produto = new Produto();
        produto.setCodigo("10");
        produto.setNome("Produto A");
        dao.cadastrar(produto);
    }

    @AfterEach
    public void tearDown() throws Exception {
        List<Produto> produtos = dao.retornaTodos();
        for (Produto produto : produtos) {
            dao.excluir(produto.getCodigo());
        }
    }

    @Test
    public void deveCadastrarProduto() throws Exception {
        Produto produtoBD = dao.consultar(produto.getCodigo());
        assertNotNull(produtoBD);
        assertNotNull(produtoBD.getId());
        assertEquals("Produto A", produtoBD.getNome());
        assertEquals("10", produtoBD.getCodigo());
    }

    @Test
    public void deveAtualizarProduto() throws Exception {
        Produto produtoBD = dao.consultar(produto.getCodigo());
        assertNotNull(produtoBD);
        assertNotNull(produtoBD.getId());
        assertEquals(produtoBD.getNome(), produto.getNome());
        assertEquals(produtoBD.getCodigo(), produto.getCodigo());

        produtoBD.setNome("Produto B");
        produtoBD.setCodigo("99");
        Integer qtdUpdate = dao.atualizar(produtoBD);
        assertTrue(qtdUpdate == 1);

        Produto produtoBD2 = dao.consultar(produtoBD.getCodigo());
        assertNotNull(produtoBD2);
        Produto produtoBD3 = dao.consultar("10");
        assertNull(produtoBD3);
        assertEquals("99", produtoBD.getCodigo());
        assertEquals("Produto B", produtoBD.getNome());
    }

    @Test
    public void deveExcluirProduto() throws Exception {
        Integer qtdDelete = dao.excluir(produto.getCodigo());
        assertTrue(qtdDelete == 1);

        Produto produtoBD = dao.consultar(produto.getCodigo());
        assertNull(produtoBD);
    }

    @Test
    public void deveRetornarTodos() throws Exception {
        produto2 = new Produto();
        produto2.setNome("Produto C");
        produto2.setCodigo("57");
        dao.cadastrar(produto2);
        List<Produto> lista = dao.retornaTodos();
        assertNotNull(lista);
        assertTrue(lista.size() == 2);
    }
}