package br.com.livro.testes;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import com.thoughtworks.xstream.XStream;

import org.junit.Assert;
import org.junit.Test;

import br.com.livro.domain.Politico;

public class TestesServlet {
	@Test
	public void testaXml() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/Prova");
		String conteudo = target.path("/politicosxml").request().get(String.class);
		System.out.println();
		Assert.assertTrue(conteudo.contains("<nome>Eduardo Soltur</nome>"));
	}
	
	@Test
	public void testaJson() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/Prova");
		String conteudo = target.path("/politicos").request().get(String.class);
		System.out.println();
		Assert.assertTrue(conteudo.contains("Dr. Alexandre Dentista"));
	}
	
	@Test
	public void testarProcuraDados() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/Prova");
		String conteudo = target.path("//politicos/10").request().get(String.class);
		System.out.println();
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
