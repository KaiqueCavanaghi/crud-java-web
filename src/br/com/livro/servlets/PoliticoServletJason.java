package br.com.livro.servlets;

import java.io.IOException;
import java.rmi.ServerException;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.livro.domain.Politico;
import br.com.livro.domain.PoliticoService;
import br.com.livro.domain.ListaPoliticos;
import br.com.livro.util.JAXBUtil;
import br.com.livro.util.ServletUtil;

@WebServlet("/politicosjson/*")
public class PoliticoServletJason extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private PoliticoService politicoService = new PoliticoService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServerException, IOException {
		List<Politico> politicos = politicoService.getPoliticos();
		ListaPoliticos lista  =  new ListaPoliticos();
		lista.setPoliticos(politicos);
		//Gera o Json
		String json = JAXBUtil.toJSON(lista);
		//Escreva o json na response do servlet com application/xml
		ServletUtil.writeJSON(resp, json);
	}
}

