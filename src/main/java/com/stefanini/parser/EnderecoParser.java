package com.stefanini.parser;

import com.stefanini.dto.EnderecoDTO;
import com.stefanini.model.Endereco;

public class EnderecoParser {

  public Endereco toEntity(EnderecoDTO dto) {
    Endereco entidade = new Endereco();
    entidade.setBairro(dto.getBairro());
    entidade.setLocalidade(dto.getLocalidade());
    entidade.setCep(dto.getCep());
    entidade.setComplemento(dto.getComplemento());
    entidade.setId(dto.getId());
    entidade.setIdPessoa(dto.getIdPessoa());
    entidade.setLogradouro(dto.getLogradouro());
    entidade.setLocalidade(dto.getLocalidade());
    return entidade;
  }

  public EnderecoDTO toDTO(Endereco entidade) {
    EnderecoDTO dto = new EnderecoDTO();
    dto.setBairro(entidade.getBairro());
    dto.setLocalidade(entidade.getLocalidade());
    dto.setCep(entidade.getCep());
    dto.setComplemento(entidade.getComplemento());
    dto.setId(entidade.getId());
    dto.setIdPessoa(entidade.getIdPessoa());
    dto.setLogradouro(entidade.getLogradouro());
    dto.setLocalidade(entidade.getLocalidade());
    return dto;
  }
  
}
