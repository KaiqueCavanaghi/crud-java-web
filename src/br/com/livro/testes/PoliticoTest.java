
package br.com.livro.testes;

import java.util.List;

import org.junit.Test;

import br.com.livro.domain.Politico;
import br.com.livro.domain.PoliticoService;
import junit.framework.TestCase;

public class PoliticoTest extends TestCase {
	private PoliticoService politicoService = new PoliticoService();
	@Test
	public void testListaPoliticos() {
		List<Politico> politicos = PoliticoService.getPoliticos();
		assertNotNull(politicos);
		// Valida se encontrou algo
		assertTrue(politicos.size() > 0);
		// Valida se encontrou o Marcelo Seminaldo
		Politico marcelo = politicoService.findByName("Marcelo Seminaldo").get(0);
		assertEquals("Marcelo Seminaldo", marcelo.getNome());
		// Valida se encontrou o Professor Jesus
		Politico professor = politicoService.findByName("Professor Jesus").get(0);
		assertEquals("Professor Jesus", professor.getNome());
		// Valida se encontrou o Romildo Santos
		Politico romildo = politicoService.findByName("Romildo Santos").get(0);
		assertEquals("Romildo Santos", romildo.getNome());
	}
	public void testSalvarDeletarPolitico() {
		Politico p = new Politico();
		p.setNome("Teste");
		p.setPartido("Teste");
		p.setDescricao("Teste");
		p.setDtnascimento("1992-6-23");
		p.setEmail("teste");
		p.setPerfilfacebook("Teste");
		politicoService.save(p);
		//id do Politico salvo
		Long id = p.getId();
		assertNotNull(id);
		//busca no banco de dados para confirmar que o Politico foi salvo
		p = politicoService.getPolitico(id);
		assertEquals("Teste",p.getNome());
		assertEquals("Teste", p.getPartido());
		assertEquals("Teste",p.getDescricao());
		//assertEquals("1992-6-23", p.getDtnascimento());
		assertEquals("teste",p.getEmail());
		assertEquals("Teste",p.getPerfilfacebook());
		//Atualiza o Politico
		p.setNome("Teste Update");
		politicoService.save(p);
		//Busca o Politico novamente (vai estar autualizado)
		p = politicoService.getPolitico(id);
		assertEquals("Teste Update",p.getNome());
		//Deleta o Politico
		politicoService.delete(id);
		//Busca o Politico novamente
		p = politicoService.getPolitico(id);
		//Desta vez o Politico n�o existe mais
		assertNull(p);
	}
}