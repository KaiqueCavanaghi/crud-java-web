package br.com.livro.domain;

import java.io.Serializable;
public class Politico implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id; 			 	
    private String nome; 		 	
    private String partido; 	 	
    private String descricao;	 	
	private String dtnascimento; 
    private String email;		 	
    private String perfilfacebook;	
        	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPartido() {
		return partido;
	}

	public void setPartido(String partido) {
		this.partido = partido;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDtnascimento() {
		return dtnascimento;
	}

	public void setDtnascimento(String dtnascimento) {
		this.dtnascimento = dtnascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPerfilfacebook() {
		return perfilfacebook;
	}

	public void setPerfilfacebook(String perfilfacebook) {
		this.perfilfacebook = perfilfacebook;
	}

	@Override
	public String toString() {
		return "Politico [id=" + id + ", nome=" + nome + ", partido=" + partido + ", descricao=" + descricao + ", dtnascimento=" + dtnascimento + ", email=" + email + ", perfilfacebook=" + perfilfacebook + "]";
	}
}