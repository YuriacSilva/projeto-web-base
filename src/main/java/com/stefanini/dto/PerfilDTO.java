package com.stefanini.dto;

import java.time.LocalDateTime;
import java.util.Set;

import javax.validation.constraints.NotNull;

public class PerfilDTO {

  private Long id;
  private String nome;
  private String descricao;
  private LocalDateTime dataHoraInclusao;
  private LocalDateTime dataHoraAlteracao;
  private Set<PessoaDTO> pessoas;

  public PerfilDTO() {
  }

  public PerfilDTO(
      @NotNull String nome,
      @NotNull String descricao,
      @NotNull LocalDateTime dataHoraInclusao,
      LocalDateTime dataHoraAlteracao) {
    this.nome = nome;
    this.descricao = descricao;
    this.dataHoraInclusao = dataHoraInclusao;
    this.dataHoraAlteracao = dataHoraAlteracao;
  }

  public Set<PessoaDTO> getPessoas() {
    return pessoas;
  }

  public void setPessoas(Set<PessoaDTO> pessoas) {
    this.pessoas = pessoas;
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

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public LocalDateTime getDataHoraInclusao() {
    return dataHoraInclusao;
  }

  public void setDataHoraInclusao(LocalDateTime dataHoraInclusao) {
    this.dataHoraInclusao = dataHoraInclusao;
  }

  public LocalDateTime getDataHoraAlteracao() {
    return dataHoraAlteracao;
  }

  public void setDataHoraAlteracao(LocalDateTime dataHoraAlteracao) {
    this.dataHoraAlteracao = dataHoraAlteracao;
  }

}
