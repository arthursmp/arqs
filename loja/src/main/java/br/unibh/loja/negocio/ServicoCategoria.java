package br.unibh.loja.negocio;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import br.unibh.loja.entidades.Categoria;

@Stateless
@LocalBean

@NamedQueries({
	@NamedQuery(name="Categoria.findByName", query = "select o from Categoria o where o.descricao like :descricao")
})

public class ServicoCategoria implements DAO<Categoria, Long> {

	@Inject
	EntityManager em;
	@Inject
	private Logger log;

	public Categoria insert(Categoria t) throws Exception {
		log.info("Persistindo " + t);
		em.persist(t);
		return t;
	}

	public Categoria update(Categoria t) throws Exception {
		log.info("Atualizando " + t);
		return em.merge(t);
	}

	public void delete(Categoria t) throws Exception {
		log.info("Removendo " + t);
		Object c = em.merge(t);
		em.remove(c);
	}

	public Categoria find(Long k) throws Exception {
		log.info("Encontrando pela chave " + k);
		return em.find(Categoria.class, k);
	}

	@SuppressWarnings("unchecked")
	public List<Categoria> findAll() throws Exception {
		log.info("Encontrando todos os objetos");
		return em.createQuery("from Categoria").getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Categoria> findByName(String descricao) throws Exception {
		log.info("Encontrando o " + descricao);
		return em.createNamedQuery("Categoria.findByName").setParameter("descricao", "%" + descricao + "%").getResultList();
	}

}
