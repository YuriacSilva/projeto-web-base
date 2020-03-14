package com.stefanini.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import com.stefanini.dto.PessoaDTO;
import com.stefanini.dto.PessoaPerfilDTO;
import com.stefanini.model.Pessoa;
import com.stefanini.model.PessoaPerfil;

public class PessoaPerfilParser {
  
  private PessoaParser pessoaParser = new PessoaParser();
  
  private PerfilParser perfilParser =  new PerfilParser();

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
  
  public List<PessoaPerfilDTO> toDTOList(List<PessoaPerfil> lista){
    return lista.stream().map(this::toDTO).collect(Collectors.toList());
  }
  
  public List<PessoaPerfil> toEntityList(List<PessoaPerfilDTO> lista){
    List<PessoaPerfil> retorno = new ArrayList<>();
    for (PessoaPerfilDTO dto : lista) {
      retorno.add(toEntity(dto));
    }
    return retorno;
  }
  
}

