package br.com.livro.domain;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="politicos")
public class ListaPoliticos implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<Politico> politicos;
	@XmlElement(name="politico")
	public List<Politico> getPoliticos(){
		System.out.println(“Mudança para deste de pull request”);
		return politicos;
	}
	public void setPoliticos(List<Politico> politicos) {
		this.politicos = politicos;
	}
	@Override
	public String toString() {
		return "ListaPoliticos [politicos="+ politicos+"]";
	}
}
