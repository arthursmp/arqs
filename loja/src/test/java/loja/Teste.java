package loja;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;

import br.unibh.loja.entidades.Categoria;
import br.unibh.loja.entidades.Cliente;
import br.unibh.loja.entidades.Produto;


public class Teste {

	@Test
	public void testeProduto() {
		
		//Produto
		Produto p1 = new Produto(1L, "Caixa de sapato");
		Produto p2 = new Produto(2L, "Caixa de sapato");
		
		p2.toString();
		p1.toString();
		assertEquals(p1.equals(p2),false);
		
		
	}
	@Test
	public void testeCliente() {
	//Cliente
		
		Cliente c1 = new Cliente(1L, "Arthur", "arthur.pinheiro", "1q2w", "Teste01", "00000000001", "12345678",
				"arthur.teste@teste.com", new Date(), new Date());
		
		Cliente c2 = new Cliente(1L, "Arthur", "arthur.pinheiro", "1q2w", "Teste01", "00000000001", "12345678",
				"arthur.teste@teste.com", new Date(), new Date());
		
		c2.toString();
		c1.toString();
		assertEquals(c1.equals(c2),true);
	}
	@Test
	public void testeCategoria() {
		//Categoria
		
		Categoria ca1 = new Categoria(6L, "Teste", "Teste01", new Categoria(), new BigDecimal(1.5), "nike");
		Categoria ca2 = new Categoria(6L, "Teste", "Teste01", new Categoria(), new BigDecimal(1.5), "nike");
		
		ca1.toString();
		ca2.toString();
		assertEquals(ca1.equals(ca1),true);
	}

}
