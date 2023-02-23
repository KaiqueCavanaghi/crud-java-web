package br.com.livro.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PoliticoDAO extends BaseDAO {
	public Politico getPoliticoById(Long id) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("select * from politico where id=?");
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				Politico p = createPolitico(rs);
				rs.close();
				return p;
			}
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}

	public List<Politico> findByName(String name) throws SQLException {
		List<Politico> politicos = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("select * from politico where lower(nome) like ?");
			stmt.setString(1, "%" + name.toLowerCase() +"%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Politico p = createPolitico(rs);
				politicos.add(p);
			}
			rs.close();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return politicos;
	}

	public List<Politico> findByPartido(String partido) throws SQLException {
		List<Politico> politicos = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("select * from politico where partido = ?");
			stmt.setString(1, partido);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Politico p = createPolitico(rs);
				politicos.add(p);
			}
			rs.close();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return politicos;
	}

	public List<Politico> getPoliticos() throws SQLException {
		List<Politico> politicos = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("select * from politico");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Politico p = createPolitico(rs);
				politicos.add(p);
			}
			rs.close();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return politicos;
	}

	public Politico createPolitico(ResultSet rs) throws SQLException {
		Politico p = new Politico();
		p.setId(rs.getLong("id"));
		p.setNome(rs.getString("nome"));
		p.setPartido(rs.getString("partido"));
		p.setDescricao(rs.getString("descricao"));
		p.setDtnascimento(rs.getString("dtnascimento"));
		p.setEmail(rs.getString("email"));
		p.setPerfilfacebook(rs.getString("perfilfacebook"));
		return p;
	}

	public void save(Politico p) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			if (p.getId() == null) {
				stmt = conn
						.prepareStatement(
								"INSERT INTO politico (nome,partido,descricao,dtnascimento,email,perfilfacebook) VALUES(?,?,?,?,?,?)",
								Statement.RETURN_GENERATED_KEYS);
			} else {
				stmt = conn
						.prepareStatement("update politico set nome=?,partido=?,descricao=?,dtnascimento=?,email=?,perfilfacebook=? where id=?");
			}
			stmt.setString(1, p.getNome());
			stmt.setString(2, p.getPartido());
			stmt.setString(3, p.getDescricao());
			stmt.setString(4, p.getDtnascimento());
			stmt.setString(5, p.getEmail());
			stmt.setString(6, p.getPerfilfacebook());
			System.out.print(stmt);
			if (p.getId() != null) {
				// Update
				stmt.setLong(7,p.getId());				
			}
			int count = stmt.executeUpdate();
			if (count == 0) {
				throw new SQLException("Erro ao inserir o politico");
			}
			// Se inseriu, ler o id auto incremento
			if (p.getId() == null) {
				Long id = getGeneratedId(stmt);
				p.setId(id);
			}
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}

	// id gerado com o campo auto incremento
	public static Long getGeneratedId(Statement stmt) throws SQLException {
		ResultSet rs = stmt.getGeneratedKeys();
		if (rs.next()) {
			Long id = rs.getLong(1);
			return id;
		}
		return 0L;
	}

	public boolean delete(Long id) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("delete from politico where id=?");
			stmt.setLong(1, id);
			int count = stmt.executeUpdate();
			boolean ok = count > 0;
			return ok;
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}
}