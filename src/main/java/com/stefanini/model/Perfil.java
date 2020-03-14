package com.stefanini.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TB_PERFIL")
public class Perfil implements Serializable {
  
  
  /**
   * Serializacao da Classe
   */
  private static final long serialVersionUID = 1L;
  /**
   * ID da tabela
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "co_seq_perfil")
  private Long id;
  /**
   * nome do perfil
   */
  @NotNull
  @Column(name = "no_perfil")
  private String nome;
  /**
   * descricao do parfil
   */
  @NotNull
  @Column(name = "ds_perfil")
  private String descricao;
  /**
   * data e hora de inclusao do perfil
   */
  @NotNull
  @Column(name = "dt_hora_inclusao")
  private LocalDateTime dataHoraInclusao;
  /**
   * date e hgora da alteracao do perfil
   */
  @Column(name = "dt_hora_alteracao")
  private LocalDateTime dataHoraAlteracao;

  /**
   * Mapeamento de Pessoa
   */
  @ManyToMany(mappedBy = "perfis")
  private Set<Pessoa> pessoas;

  public Perfil() {
  }

  public Perfil(
      @NotNull String nome,
      @NotNull String descricao,
      @NotNull LocalDateTime dataHoraInclusao,
      LocalDateTime dataHoraAlteracao) {
    this.nome = nome;
    this.descricao = descricao;
    this.dataHoraInclusao = dataHoraInclusao;
    this.dataHoraAlteracao = dataHoraAlteracao;
  }

  public Set<Pessoa> getPessoas() {
    return pessoas;
  }

  public void setPessoas(Set<Pessoa> pessoas) {
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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((dataHoraAlteracao == null) ? 0 : dataHoraAlteracao.hashCode());
    result = prime * result + ((dataHoraInclusao == null) ? 0 : dataHoraInclusao.hashCode());
    result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((nome == null) ? 0 : nome.hashCode());
    result = prime * result + ((pessoas == null) ? 0 : pessoas.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Perfil other = (Perfil) obj;
    if (dataHoraAlteracao == null) {
      if (other.dataHoraAlteracao != null)
        return false;
    } else if (!dataHoraAlteracao.equals(other.dataHoraAlteracao))
      return false;
    if (dataHoraInclusao == null) {
      if (other.dataHoraInclusao != null)
        return false;
    } else if (!dataHoraInclusao.equals(other.dataHoraInclusao))
      return false;
    if (descricao == null) {
      if (other.descricao != null)
        return false;
    } else if (!descricao.equals(other.descricao))
      return false;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (nome == null) {
      if (other.nome != null)
        return false;
    } else if (!nome.equals(other.nome))
      return false;
    if (pessoas == null) {
      if (other.pessoas != null)
        return false;
    } else if (!pessoas.equals(other.pessoas))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Perfil [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", dataHoraInclusao=" + dataHoraInclusao
        + ", dataHoraAlteracao=" + dataHoraAlteracao + ", pessoas=" + pessoas + "]";
  }

}
