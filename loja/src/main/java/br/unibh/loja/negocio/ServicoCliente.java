package br.unibh.loja.negocio;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Years;

import br.unibh.loja.entidades.Cliente;

@Stateless
@LocalBean

@NamedQueries({ @NamedQuery(name = "Cliente.findByName", query = "select o from Cliente o where o.cpf like :cpf") })

public class ServicoCliente implements DAO<Cliente, Long> {

	@Inject
	EntityManager em;

	@Inject
	private Logger log;

	public Cliente insert(Cliente t) throws Exception {
		log.info("Persistindo " + t);
		if (t.getPerfil().equals("Standard")) {
			em.persist(t);
		} else {
			throw new Exception("Esse perfil de usuário não é válido");
		}

		return t;
	}

	public Cliente update(Cliente t) throws Exception {
		log.info("Atualizando " + t);
		

		// datacasdastro
		Date dateC = t.getDataCadastro();
		DateTime dateTimeC = new DateTime(dateC);
		
		DateTime start = dateTimeC;
		DateTime end = new DateTime();
		
		Days d = Days.daysBetween(start, end);
		Years y = Years.yearsBetween(start, end);
		  
		//Análise de perfil de usuário

		if (y.getYears() < 1) {
			// t.setPerfil("Standard");
		}
		if (y.getYears() < 5 && y.getYears() > 1) {
			t.setPerfil("Standard, Premium");
		}
		if (y.getYears() > 5) {
			t.setPerfil("Standard, Premium, Gold");
		}
		return em.merge(t);
	}

	public void delete(Cliente t) throws Exception {
		log.info("Removendo " + t);
		Object c = em.merge(t);
		em.remove(c);
	}

	public Cliente find(Long k) throws Exception {
		log.info("Encontrando pela chave " + k);
		return em.find(Cliente.class, k);
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> findAll() throws Exception {
		log.info("Encontrando todos os objetos");
		return em.createQuery("from Cliente").getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> findByName(String name) throws Exception {
		log.info("Encontrando o " + name);
		return em.createNamedQuery("Cliente.findByName").setParameter("cpf", "%" + name + "%").getResultList();
	}

}
