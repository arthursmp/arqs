package loja;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.unibh.loja.entidades.Categoria;
import br.unibh.loja.entidades.Cliente;
import br.unibh.loja.entidades.Produto;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class TesteValidation {

	private static Validator validator;

	@BeforeClass
	public static void setUp() {
		System.out.println("Inicializando validador...");
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	// Válido
	@Test
	public void testeValidacaoCliente1() {
		Cliente c1 = new Cliente(1L, "Arthur", "arthur.pinheiro", "1q2w", "Teste01", "00000000001", "(99)09999-9999",
				"arthur.teste@teste.com", new Date(), new Date());
		System.out.print(c1);

		Set<ConstraintViolation<Cliente>> constraintViolations = validator.validate(c1);
		for (ConstraintViolation<Cliente> c : constraintViolations) {
			System.out.println(" Erro de Validacao: " + c.getMessage());
		}
		Assert.assertEquals(2, constraintViolations.size());
	}

	// Inválido
	@Test
	public void testeValidacaoCliente2() {
		Cliente c1 = new Cliente(1L, "", "arthur.pinheiro", "1q2w", "Teste01", "00000000001", "(99)09999-9999",
				"arthur.teste@teste.com", new Date(), new Date());
		System.out.print(c1);

		Set<ConstraintViolation<Cliente>> constraintViolations = validator.validate(c1);
		for (ConstraintViolation<Cliente> c : constraintViolations) {
			System.out.println(" Erro de Validacao: " + c.getMessage());
		}
		Assert.assertEquals(4, constraintViolations.size());
	}

	// CATEGORIA

	// Válido
	@Test
	public void testeValidacaoCategoria1() {
		Categoria ca1 = new Categoria(1L, "Caixa de sapato");
		System.out.print(ca1);

		Set<ConstraintViolation<Categoria>> constraintViolations = validator.validate(ca1);
		for (ConstraintViolation<Categoria> c : constraintViolations) {
			System.out.println(" Erro de Validacao: " + c.getMessage());
		}
		Assert.assertEquals(0, constraintViolations.size());
	}
	
	// Inválido
	@Test
	public void testeValidacaoCategoria2() {
		Categoria ca1 = new Categoria(1L, "");
		System.out.print(ca1);

		Set<ConstraintViolation<Categoria>> constraintViolations = validator.validate(ca1);
		for (ConstraintViolation<Categoria> c : constraintViolations) {
			System.out.println(" Erro de Validacao: " + c.getMessage());
		}
		Assert.assertEquals(2, constraintViolations.size());
	}
	
	//PRODUTO
	
	@Test
	public void testeValidacaoProduto1() {
		Produto p1 = new Produto(6L, "Teste", "Teste01", new Categoria(), new BigDecimal(1.5), "nike");
		System.out.print(p1);

		Set<ConstraintViolation<Produto>> constraintViolations = validator.validate(p1);
		for (ConstraintViolation<Produto> c : constraintViolations) {
			System.out.println(" Erro de Validacao: " + c.getMessage());
		}
		Assert.assertEquals(0, constraintViolations.size());
	}
	

	@Test
	public void testeValidacaoProduto2() {
		Produto p1 = new Produto(6L, "", "Teste01", new Categoria(), new BigDecimal(1.5), "nike");
		System.out.print(p1);

		Set<ConstraintViolation<Produto>> constraintViolations = validator.validate(p1);
		for (ConstraintViolation<Produto> c : constraintViolations) {
			System.out.println(" Erro de Validacao: " + c.getMessage());
		}
		Assert.assertEquals(2, constraintViolations.size());
	}
	
}
