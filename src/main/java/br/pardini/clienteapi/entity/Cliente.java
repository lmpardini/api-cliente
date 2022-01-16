package br.pardini.clienteapi.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//Entidade que cria a tabela no banco de dados com seus atributos e respectivos getters e setters;

@Entity
@Table(name="TB_Cliente")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private long id;
	
	 @Column(name = "nome", nullable = false)
	private String nome;
	 
	 @Column(name = "email", nullable = false)
	private String email;
	 
	 @Column(name = "telefone", nullable = false)
	private String telefone;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", email=" + email + ", telefone=" + telefone + "]";
	}
	 
	 
	
	
	
	
	

}
