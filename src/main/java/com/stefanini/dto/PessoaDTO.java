package com.stefanini.dto;

import java.time.LocalDate;
import java.util.Set;

public class PessoaDTO {

	private Long id;
	private String nome;
	private String email;
	private LocalDate dataNascimento; 
	private Boolean situacao;
	private Set<EnderecoDTO> enderecos;
	//private Set<PerfilDTO> perfils;

	public PessoaDTO() {
	}

//	public Set<PerfilDTO> getPerfils() {
//		return perfils;
//	}
//
//	public void setPerfils(Set<PerfilDTO> perfils) {
//		this.perfils = perfils;
//	}

	public PessoaDTO(String nome, String email, LocalDate dataNascimento, Boolean situacao) {
		super();
		this.nome = nome;
		this.email = email;
		this.dataNascimento = dataNascimento;
		this.situacao = situacao;
	}

	public Set<EnderecoDTO> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(Set<EnderecoDTO> enderecos) {
		this.enderecos = enderecos;
	}


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

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getSituacao() {
		return situacao;
	}

	public void setSituacao(Boolean situacao) {
		this.situacao = situacao;
	}

}
