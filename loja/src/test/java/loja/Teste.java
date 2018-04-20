package loja;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;

import br.unibh.loja.entidades.Produto;
import br.unibh.loja.entidades.Cliente;
import br.unibh.loja.entidades.Categoria;

public class Teste {

	@Test
	public void testeCategoria() {

		// Categoria
		Categoria ca1 = new Categoria(1L, "Caixa de sapato");
		Categoria ca2 = new Categoria(2L, "Caixa de sapato");

		ca2.toString();
		ca1.toString();
		assertEquals(ca1.equals(ca2), false);

	}

	@Test
	public void testeCliente() {
		// Cliente

		Cliente c1 = new Cliente(1L, "Arthur", "arthur.pinheiro", "1q2w", "Teste01", "00000000001", "12345678",
				"arthur.teste@teste.com", new Date(), new Date());

		Cliente c2 = new Cliente(1L, "Arthur", "arthur.pinheiro", "1q2w", "Teste01", "00000000001", "12345678",
				"arthur.teste@teste.com", new Date(), new Date());

		c2.toString();
		c1.toString();
		assertEquals(c1.equals(c2), true);
	}

	@Test
	public void testeProduto() {
		// Produto

		Produto p1 = new Produto(6L, "Teste", "Teste01", new Categoria(), new BigDecimal(1.5), "nike");
		Produto p2 = new Produto(6L, "Teste", "Teste01", new Categoria(), new BigDecimal(1.5), "nike");

		p1.toString();
		p2.toString();
		assertEquals(p2.equals(p1), true);
	}

}
