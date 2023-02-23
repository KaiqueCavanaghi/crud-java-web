package br.com.livro.testes;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.junit.Assert;
import org.junit.Test;

public class TesteRest{
	@Test
	public void testaConexaoServidor() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/Prova");
		String conteudo = target.path("/rest/politicos/").
				request().get(String.class);
		Assert.assertTrue(conteudo.contains("Dr. Alexandre Dentista"));
	}
	@Test
	public void testarProcuraDados() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/Prova");
		String conteudo = target.path("/rest/politicos/10").
				request().get(String.class);
		Assert.assertTrue(conteudo.contains("João Dárcio"));
		Assert.assertTrue(conteudo.contains("1970-12-18"));
	}
	@Test
	public void testarProcuraNaoAchado() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/Prova");
		String conteudo = target.path("/rest/politicos/9").
				request().get(String.class);
		Assert.assertTrue(!conteudo.contains("João Dárcio"));
		Assert.assertTrue(!conteudo.contains("1970-12-18"));
	}
	
}
