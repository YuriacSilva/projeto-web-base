package com.stefanini.dto;

import java.io.Serializable;

public class PessoaPerfilDTO implements Serializable {

  /**
   * serial da classe
   */
  private static final long serialVersionUID = 1L;

  private Long id;

  private PerfilDTO perfil;
  private PessoaDTO pessoa;

  public PessoaPerfilDTO() {
  }

  public PessoaPerfilDTO(PerfilDTO perfil, PessoaDTO pessoa) {
    this.perfil = perfil;
    this.pessoa = pessoa;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public PerfilDTO getPerfil() {
    return perfil;
  }

  public void setPerfil(PerfilDTO perfil) {
    this.perfil = perfil;
  }

  public PessoaDTO getPessoa() {
    return pessoa;
  }

  public void setPessoa(PessoaDTO pessoa) {
    this.pessoa = pessoa;
  }

  
}
