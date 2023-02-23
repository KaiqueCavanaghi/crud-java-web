package br.com.livro.servlets;

import java.io.IOException;
import java.rmi.ServerException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.livro.domain.Politico;
import br.com.livro.domain.PoliticoService;
import br.com.livro.domain.Response;
import br.com.livro.util.RegexUtil;
import br.com.livro.util.ServletUtil;

@WebServlet("/politicos/*")
public class PoliticoServletGson extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private PoliticoService politicoService = new PoliticoService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String requestUri = req.getRequestURI();
		Long id = RegexUtil.matchId(requestUri);
		if(id != null) {
			Politico politico = politicoService.getPolitico(id);
			if(politico != null) {
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				String json = gson.toJson(politico);
				ServletUtil.writeJSON(resp, json);
			}else {
				resp.sendError(404,"Politico não encontrado");
			}
		}else {
			List<Politico> politicos = PoliticoService.getPoliticos();
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(politicos);
			ServletUtil.writeJSON(resp, json);
		}
	}
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse resp) throws IOException, ServletException {
		Politico politico = getPoliticoFromRequest(request);
		politicoService.save(politico);
		Gson gson  = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(politico);
		ServletUtil.writeJSON(resp, json);
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String requestUri = req.getRequestURI();
		Long id = RegexUtil.matchId(requestUri);
		if(id != null) {
			politicoService.delete(id);
			Response r = Response.Ok("Politico excluido com sucesso");
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(r);
			ServletUtil.writeJSON(resp, json);
			resp.sendError(200, "Politico excluido com sucesso");
		}else {
			resp.sendError(404,"URL invalida");
		}
	}
	
	private Politico getPoliticoFromRequest(HttpServletRequest request) {
		Politico p = new Politico();
		String id = request.getParameter("id");
		if(id != null) {
			p = politicoService.getPolitico(Long.parseLong(id));
		}
		p.setNome(request.getParameter("nome"));
		p.setPartido(request.getParameter("partido"));
		p.setDescricao(request.getParameter("descricao"));
		p.setDtnascimento(request.getParameter("dtnascimento"));
		p.setEmail(request.getParameter("email"));
		p.setPerfilfacebook(request.getParameter("perfilfacebook"));
		return p;
	}
}
