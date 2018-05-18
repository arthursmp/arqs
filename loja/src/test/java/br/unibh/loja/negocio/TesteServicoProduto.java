package br.unibh.loja.negocio;


	import static org.junit.Assert.assertEquals;
	import static org.junit.Assert.assertNotNull;
	import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Date;
	import java.util.logging.Logger;
	import javax.inject.Inject;
	import org.jboss.arquillian.container.test.api.Deployment;
	import org.jboss.arquillian.junit.Arquillian;
	import org.jboss.shrinkwrap.api.Archive;
	import org.jboss.shrinkwrap.api.ShrinkWrap;
	import org.jboss.shrinkwrap.api.asset.EmptyAsset;
	import org.jboss.shrinkwrap.api.spec.WebArchive;
	import org.junit.FixMethodOrder;
	import org.junit.Test;
	import org.junit.runner.RunWith;
	import org.junit.runners.MethodSorters;
	import br.unibh.loja.entidades.Categoria;
	import br.unibh.loja.entidades.Cliente;
	import br.unibh.loja.entidades.Produto;
	import br.unibh.loja.util.Resources;

	@RunWith(Arquillian.class)
	@FixMethodOrder(MethodSorters.NAME_ASCENDING)

	public class TesteServicoProduto {

		@Deployment
		public static Archive<?> createTestArchive() {
			// Cria o pacote que vai ser instalado no Wildfly para realizacao dos testes
			return ShrinkWrap.create(WebArchive.class, "testeloja.war")
					.addClasses(Categoria.class, Cliente.class, Produto.class, Resources.class, DAO.class,
							ServicoProduto.class)
					.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
					.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
		}

		// Realiza as injecoes com CDI
		@Inject
		private Logger log;
		@Inject
		private ServicoProduto sp;

		@Test
		public void teste01_inserirSemErro() throws Exception {
			log.info("============> Iniciando o teste " + Thread.currentThread().getStackTrace()[1].getMethodName());
			Categoria cc = new Categoria (6L, "LATA");
			
			Produto p1 = new Produto(6L, "Teste", "Teste01", cc, new BigDecimal(1.5), "nike");
			sp.insert(p1);
			
			Produto aux = (Produto) sp.findByName("Teste").get(0);
			assertNotNull(aux);
			
			log.info("============> Finalizando o teste " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}

		@Test
		public void teste02_inserirComErro() throws Exception {
			log.info("============> Iniciando o teste " + Thread.currentThread().getStackTrace()[1].getMethodName());
			try {
				Produto p1 = new Produto(6L, "Teste@@@@@", "Teste01", new Categoria (6L, "LATA"), new BigDecimal(1.5), "nike");
				sp.insert(p1);
			} catch (Exception e) {
				assertTrue(checkString(e, "Caracteres permitidos: letras, espaços, ponto e aspas simples"));
			}
			log.info("============> Finalizando o teste " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}

		@Test
		public void teste03_atualizar() throws Exception {
			log.info("============> Iniciando o teste " + Thread.currentThread().getStackTrace()[1].getMethodName());
			Produto p1 = (Produto) sp.findByName("Teste").get(0);
			p1.setNome("arthur");
			sp.update(p1);
			Produto aux = (Produto) sp.findByName("arthur").get(0);
			assertNotNull(aux);
			log.info("============> Finalizando o teste " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}

		@Test
		public void teste04_excluir() throws Exception {
			log.info("============> Iniciando o teste " + Thread.currentThread().getStackTrace()[1].getMethodName());
			Produto p1 = (Produto) sp.findByName("arthur").get(0);
			sp.delete(p1);
			assertEquals(0, sp.findByName("arthur").size());
			log.info("============> Finalizando o teste " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}

		private boolean checkString(Throwable e, String str) {
			if (e.getMessage().contains(str)) {
				return true;
			} else if (e.getCause() != null) {
				return checkString(e.getCause(), str);
			}
			return false;
		}
	}
