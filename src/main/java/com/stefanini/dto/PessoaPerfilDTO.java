package com.stefanini.dto;

import javax.persistence.*;
import java.io.Serializable;

public class PessoaPerfilDTO implements Serializable {


    private Long id;

    private PerfilDTO perfil;
    private PessoaDTO pessoa;

    public PessoaPerfilDTO() {
		// TODO Auto-generated constructor stub
	}

    public PessoaPerfilDTO(PerfilDTO perfil, PessoaDTO pessoa) {
        this.perfil = perfil;
        this.pessoa = pessoa;
    }

    public void setPessoa(PessoaDTO pessoa) {
        this.pessoa = pessoa;
    }

    public PerfilDTO getPerfil() {
        return perfil;
    }

    public PessoaDTO getPessoa() {
        return pessoa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
