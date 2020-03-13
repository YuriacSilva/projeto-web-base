package com.stefanini.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_pessoa_perfil")
public class PessoaPerfil implements Serializable {

  /**
   * serial da classe
   */
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "co_seq_pessoal_perfil")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "co_seq_perfil", referencedColumnName = "co_seq_perfil", nullable = false)
  private Perfil perfil;
  @ManyToOne
  @JoinColumn(name = "co_seq_pessoa", referencedColumnName = "co_seq_pessoa", nullable = false)
  private Pessoa pessoa;

  public PessoaPerfil() {
  }

  public PessoaPerfil(Perfil perfil, Pessoa pessoa) {
    this.perfil = perfil;
    this.pessoa = pessoa;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Perfil getPerfil() {
    return perfil;
  }

  public void setPerfil(Perfil perfil) {
    this.perfil = perfil;
  }

  public Pessoa getPessoa() {
    return pessoa;
  }

  public void setPessoa(Pessoa pessoa) {
    this.pessoa = pessoa;
  }

}
