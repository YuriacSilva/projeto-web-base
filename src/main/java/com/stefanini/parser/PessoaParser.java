package com.stefanini.parser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import com.stefanini.dto.EnderecoDTO;
import com.stefanini.dto.PessoaDTO;
import com.stefanini.model.Endereco;
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

  public PessoaDTO toDTO(Pessoa entity) {
    PessoaDTO pessoaDto = new PessoaDTO();
    pessoaDto.setEnderecos(new HashSet<>());
    pessoaDto.setId(entity.getId());
    pessoaDto.setDataNascimento(entity.getDataNascimento());
    pessoaDto.setEmail(entity.getEmail());
    pessoaDto.setEnderecos(entity.getEnderecos().stream().map(enderecoParser::toDTO).collect(Collectors.toSet()));
    pessoaDto.setNome(entity.getNome());
    pessoaDto.setSituacao(entity.getSituacao());
    return pessoaDto;
  }

  public List<PessoaDTO> toDTOList(List<Pessoa> lista){
    return lista.stream().map(this::toDTO).collect(Collectors.toList());
  }
  
  public List<Pessoa> toEntityList(List<PessoaDTO> lista){
    List<Pessoa> retorno = new ArrayList<>();
    for (PessoaDTO pessoaDTO : lista) {
      retorno.add(toEntity(pessoaDTO));
    }
    return retorno;
  }
  
}
