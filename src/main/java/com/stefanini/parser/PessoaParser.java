package com.stefanini.parser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.stefanini.dto.PessoaDTO;
import com.stefanini.model.Pessoa;

public class PessoaParser {

  private EnderecoParser enderecoParser = new EnderecoParser();
  
  private PerfilParser perfilParser = new PerfilParser();
  
  public Pessoa toEntity(PessoaDTO dto) {
    if(Objects.isNull(dto.getEnderecos())) {
      dto.setEnderecos(new HashSet<>());
    }
    if(Objects.isNull(dto.getPerfis())) {
      dto.setPerfis(new HashSet<>());
    }
    Pessoa entidade = new Pessoa();
    entidade.setId(dto.getId());
    entidade.setDataNascimento(dto.getDataNascimento());
    entidade.setEmail(dto.getEmail());
    entidade.setEnderecos(new HashSet<>());
    entidade.setEnderecos(dto.getEnderecos().stream().map(enderecoParser::toEntity).collect(Collectors.toSet()));
    entidade.setNome(dto.getNome());
    entidade.setSituacao(dto.getSituacao());
    entidade.setPerfis(new HashSet<>());
    entidade.setPerfis(dto.getPerfis().stream().map(perfilParser::toEntity).collect(Collectors.toSet()));
    return entidade;
  }

  public PessoaDTO toDTO(Pessoa entidade) {
    if(Objects.isNull(entidade.getEnderecos())) {
      entidade.setEnderecos(new HashSet<>());
    }
    if(Objects.isNull(entidade.getPerfis())) {
      entidade.setPerfis(new HashSet<>());
    }
    PessoaDTO dto = new PessoaDTO();
    dto.setId(entidade.getId());
    dto.setDataNascimento(entidade.getDataNascimento());
    dto.setEmail(entidade.getEmail());
    dto.setEnderecos(new HashSet<>());
    dto.setEnderecos(entidade.getEnderecos().stream().map(enderecoParser::toDTO).collect(Collectors.toSet()));
    dto.setNome(entidade.getNome());
    dto.setSituacao(entidade.getSituacao());
    dto.setPerfis(new HashSet<>());
    dto.setPerfis(entidade.getPerfis().stream().map(perfilParser::toDTO).collect(Collectors.toSet()));
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
