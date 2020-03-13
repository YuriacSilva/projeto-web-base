package com.stefanini.parser;

import javax.inject.Inject;

import com.stefanini.dto.PessoaPerfilDTO;
import com.stefanini.model.PessoaPerfil;

public class PessoaPerfilParser {
  
  @Inject
  private PessoaParser pessoaParser;
  
  @Inject
  private PerfilParser perfilParser;

  public PessoaPerfil toEntity(PessoaPerfilDTO dto) {
    PessoaPerfil entidade = new PessoaPerfil();
    entidade.setPessoa(pessoaParser.toEntity(dto.getPessoa()));
    entidade.setPerfil(perfilParser.toEntity(dto.getPerfil()));
    return entidade;
  }

  public PessoaPerfilDTO toDTO(PessoaPerfil entidade) {
    PessoaPerfilDTO dto = new PessoaPerfilDTO();
    dto.setPessoa(pessoaParser.toDTO(entidade.getPessoa()));
    dto.setPerfil(perfilParser.toDTO(entidade.getPerfil()));
    return dto;
  }
  
}

