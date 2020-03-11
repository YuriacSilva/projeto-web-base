package com.stefanini.dto;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

public class PerfilDTO implements Serializable {
    private Long id;
    private String nome;
    private String descricao;
    private LocalDateTime dataHoraInclusao;
    private LocalDateTime dataHoraAlteracao;

//    /**
//     * Mapeamento de Pessoa
//     */
//    @ManyToMany(mappedBy = "perfils")
//    private Set<Pessoa> pessoas;


    public PerfilDTO() {
    }

    public PerfilDTO(@NotNull String nome, @NotNull String descricao, @NotNull LocalDateTime dataHoraInclusao, LocalDateTime dataHoraAlteracao) {
        this.nome = nome;
        this.descricao = descricao;
        this.dataHoraInclusao = dataHoraInclusao;
        this.dataHoraAlteracao = dataHoraAlteracao;
//        this.pessoas = pessoas;
    }

//    public Set<Pessoa> getPessoas() {
//        return pessoas;
//    }
//
//    public void setPessoas(Set<Pessoa> pessoas) {
//        this.pessoas = pessoas;
//    }

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
    public String toString() {
        return "Perfil{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", dataHoraInclusao=" + dataHoraInclusao +
                ", dataHoraAlteracao=" + dataHoraAlteracao +
//                ", pessoas=" + pessoas +
                '}';
    }
}
