package br.unibh.loja.integracao;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.unibh.loja.entidades.Categoria;
import br.unibh.loja.negocio.ServicoCategoria;
import br.unibh.loja.negocio.ServicoProduto;
import io.swagger.annotations.Api;

@Api
@Path("categoria")
public class RestCategoria {
	@Inject
	private ServicoCategoria sc;
	private ServicoProduto sp;

	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Categoria> helloworld() throws Exception {
		return sc.findAll();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Categoria hello(@PathParam("id") final Long id) throws Exception {
		return sc.find(id);
	}

	@POST
	@Path("new")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Categoria inserirNova(Categoria c) throws Exception {
		List<Categoria> aux = sc.findByName(c.getDescricao());
		if (aux == null || aux.isEmpty()) {
			return sc.insert(c);
		} else {
			throw new Exception("Categoria já existe");
		}
	}

	@PUT
	@Path("atualizar/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Categoria atualizarC(@PathParam("id") final Long id, Categoria categoria) throws Exception {
		return sc.update(categoria);
	}

	@DELETE
	@Path("delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deletarC(@PathParam("id") final Long id) throws Exception {
		sc.delete(sc.find(id));

	}

}
