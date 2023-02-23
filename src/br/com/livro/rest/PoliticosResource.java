package br.com.livro.rest;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import br.com.livro.domain.Politico;
import br.com.livro.domain.PoliticoService;
import br.com.livro.domain.Response;

@Path("/politicos")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class PoliticosResource {
	private PoliticoService politicoService = new PoliticoService();

	@GET
	public List<Politico> get() {
		List<Politico> politicos = PoliticoService.getPoliticos();
		return politicos;
	}

	@GET
	@Path("{id}")
	public Politico get(@PathParam("id") long id) {
		Politico p = politicoService.getPolitico(id);
		return p;
	}

	@GET
	@Path("/partido/{partido}")
	public List<Politico> getByPartido(@PathParam("partido") String partido) {
		List<Politico> politicos = politicoService.findByPartido(partido);
		return politicos;
	}

	@GET
	@Path("/nome/{nome}")
	public List<Politico> getByNome(@PathParam("nome") String nome) {
		List<Politico> politicos = politicoService.findByName(nome);
		return politicos;
	}

	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") long id) {
		politicoService.delete(id);
		return Response.Ok("Politico deletado com sucesso");
	}

	@POST
	public Response post(Politico p) {
		politicoService.save(p);
		return Response.Ok("Politico salvo com sucesso");
	}

	@PUT
	public Response put(Politico p) {
		politicoService.save(p);
		return Response.Ok("Politico atualizado com sucesso");
	}
}