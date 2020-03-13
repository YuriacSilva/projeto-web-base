package com.stefanini.parser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import com.stefanini.dto.PerfilDTO;
import com.stefanini.model.Perfil;

public class PerfilParser {
  
  @Inject
  private PessoaParser pessoaParser;

  public Perfil toEntity(PerfilDTO dto) {
    Perfil entidade = new Perfil();
    entidade.setPessoas(new HashSet<>());
    entidade.setId(dto.getId());
    entidade.setDescricao(dto.getDescricao());
    entidade.setDataHoraInclusao(dto.getDataHoraInclusao());
    entidade.setDataHoraAlteracao(dto.getDataHoraAlteracao());
    entidade.setPessoas(dto.getPessoas().stream().map(pessoaParser::toEntity).collect(Collectors.toSet()));
    return entidade;
  }

  public PerfilDTO toDTO(Perfil entidade) {
    PerfilDTO dto = new PerfilDTO();
    dto.setPessoas(new HashSet<>());
    dto.setId(entidade.getId());
    dto.setDescricao(entidade.getDescricao());
    dto.setDataHoraInclusao(entidade.getDataHoraInclusao());
    dto.setDataHoraAlteracao(entidade.getDataHoraAlteracao());
    dto.setPessoas(entidade.getPessoas().stream().map(pessoaParser::toDTO).collect(Collectors.toSet()));
    return dto;
  }

  public List<PerfilDTO> toDTOList(List<Perfil> lista){
    return lista.stream().map(this::toDTO).collect(Collectors.toList());
  }
  
  public List<Perfil> toEntityList(List<PerfilDTO> lista){
    List<Perfil> retorno = new ArrayList<>();
    for (PerfilDTO dto : lista) {
      retorno.add(toEntity(dto));
    }
    return retorno;
  }
  
}
