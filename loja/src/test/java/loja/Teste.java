package loja;

import br.unibh.loja.entidades.Produto;

public class Teste {

	public static void main(String[]args) {
		
		Produto p1 = new Produto(1L, "Caixa de sapato");
		p1.toString();
		Produto p2 = new Produto(2L, "Caixa de sapato");
		p2.toString();
		
	}
}
