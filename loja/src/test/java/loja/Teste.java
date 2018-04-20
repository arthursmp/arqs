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
	public void testeProduto() {

		// Produto
		Categoria p1 = new Categoria(1L, "Caixa de sapato");
		Categoria p2 = new Categoria(2L, "Caixa de sapato");

		p2.toString();
		p1.toString();
		assertEquals(p1.equals(p2), false);

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
	public void testeCategoria() {
		// Categoria

		Produto ca1 = new Produto(6L, "Teste", "Teste01", new Produto(), new BigDecimal(1.5), "nike");
		Produto ca2 = new Produto(6L, "Teste", "Teste01", new Produto(), new BigDecimal(1.5), "nike");

		ca1.toString();
		ca2.toString();
		assertEquals(ca1.equals(ca1), true);
	}

}
