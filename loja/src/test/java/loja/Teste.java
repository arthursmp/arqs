package loja;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.unibh.loja.entidades.Produto;


public class Teste {

	@Test
	public void teste1() {
		
		Produto p1 = new Produto(1L, "Caixa de sapato");
		p1.toString();
		Produto p2 = new Produto(2L, "Caixa de sapato");
		p2.toString();
		
		
		assertEquals(p1.equals(p2),false);
		
	}
}
