package br.com.livro.domain;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PoliticoService {
	private static PoliticoDAO db = new PoliticoDAO();

	// Lista todos os politicos do banco de dados
	public static List<Politico> getPoliticos() {
		try {
			List<Politico> politicos = db.getPoliticos();
			return politicos;
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<Politico>();
		}
	}

	// Busca um politico pelo id
	public Politico getPolitico(Long id) {
		try {
			return db.getPoliticoById(id);
		} catch (SQLException e) {
			return null;
		}
	}

	// Deleta o politico pelo id
	public boolean delete(Long id) {
		try {
			return db.delete(id);
		} catch (SQLException e) {
			return false;
		}
	}

	// Salva ou atualiza o politico
	public boolean save(Politico politico) {
		try {
			db.save(politico);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	// Busca o politico pelo nome
	public List<Politico> findByName(String name) {
		try {
			return db.findByName(name);
		} catch (SQLException e) {
			return null;
		}
	}

	public List<Politico> findByPartido(String partido) {
		try {
			return db.findByPartido(partido);
		} catch (SQLException e) {
			return null;
		}
	}
}