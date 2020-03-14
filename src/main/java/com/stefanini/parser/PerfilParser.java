package com.stefanini.parser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import com.stefanini.dto.PerfilDTO;
import com.stefanini.model.Perfil;

public class PerfilParser {
  
//  private PessoaParser pessoaParser = new PessoaParser();

  public Perfil toEntity(PerfilDTO dto) {
    Perfil entidade = new Perfil();
    entidade.setPessoas(new HashSet<>());
//  entidade.setPessoas(dto.getPessoas().stream().map(pessoaParser::toEntity).collect(Collectors.toSet()));
    entidade.setId(dto.getId());
    entidade.setNome(dto.getNome());
    entidade.setDescricao(dto.getDescricao());
    entidade.setDataHoraInclusao(dto.getDataHoraInclusao());
    entidade.setDataHoraAlteracao(dto.getDataHoraAlteracao());

    return entidade;
  }

  public PerfilDTO toDTO(Perfil entidade) {
    PerfilDTO dto = new PerfilDTO();
//  dto.setPessoas(new HashSet<>());
//  dto.setPessoas(entidade.getPessoas().stream().map(pessoaParser::toDTO).collect(Collectors.toSet()));
    dto.setId(entidade.getId());
    dto.setNome(entidade.getNome());
    dto.setDescricao(entidade.getDescricao());
    dto.setDataHoraInclusao(entidade.getDataHoraInclusao());
    dto.setDataHoraAlteracao(entidade.getDataHoraAlteracao());

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
