package com.stefanini.parser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import com.stefanini.dto.PessoaDTO;
import com.stefanini.model.Pessoa;

public class PessoaParser {

  @Inject
  private EnderecoParser enderecoParser;
  
  public Pessoa toEntity(PessoaDTO dto) {
    Pessoa entidade = new Pessoa();
    entidade.setEnderecos(new HashSet<>());
    entidade.setId(dto.getId());
    entidade.setDataNascimento(dto.getDataNascimento());
    entidade.setEmail(dto.getEmail());
    entidade.setEnderecos(dto.getEnderecos().stream().map(enderecoParser::toEntity).collect(Collectors.toSet()));
    entidade.setNome(dto.getNome());
    entidade.setSituacao(dto.getSituacao());
    return entidade;
  }

  public PessoaDTO toDTO(Pessoa entidade) {
    PessoaDTO dto = new PessoaDTO();
    dto.setEnderecos(new HashSet<>());
    dto.setId(entidade.getId());
    dto.setDataNascimento(entidade.getDataNascimento());
    dto.setEmail(entidade.getEmail());
    dto.setEnderecos(entidade.getEnderecos().stream().map(enderecoParser::toDTO).collect(Collectors.toSet()));
    dto.setNome(entidade.getNome());
    dto.setSituacao(entidade.getSituacao());
    return dto;
  }

  public List<PessoaDTO> toDTOList(List<Pessoa> lista){
    return lista.stream().map(this::toDTO).collect(Collectors.toList());
  }
  
  public List<Pessoa> toEntityList(List<PessoaDTO> lista){
    List<Pessoa> retorno = new ArrayList<>();
    for (PessoaDTO dto : lista) {
      retorno.add(toEntity(dto));
    }
    return retorno;
  }
  
}
