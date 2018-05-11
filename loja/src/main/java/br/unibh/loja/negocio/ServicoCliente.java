package br.unibh.loja.negocio;

import java.time.LocalDate;
import java.time.Period;
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
import org.joda.time.Interval;

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
		if(t.getPerfil().equals("Standard")) {
			em.persist(t);
		}else {
			throw new Exception("Esse perfil de usu�rio n�o � v�lido");
		}
		
		return t;
	}

	public Cliente update(Cliente t) throws Exception {
		//log.info("Atualizando " + t);
		//LocaDate today = LocalDate.now();
		
		//Interval intervalo = new Interval(today, t.getDataCadastro());
		//Period periodo = intervalo.toPeriod();
		
		//if(periodo.getYears()<1) {
			//t.setPerfil("Standard");
		//}
		//if(periodo.getYears()<5 && periodo.getYears()>1) {
			t.setPerfil("Standard, Premium");
		//}
		//if(periodo.getYears()>5) {
		///	t.setPerfil("Standard, Premium, Gold");
		//}
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
		return em.createNamedQuery("Cliente.findByName").setParameter("nome", "%" + name + "%").getResultList();
	}

}
