package com.stefanini.parser;

import com.stefanini.dto.EnderecoDTO;
import com.stefanini.model.Endereco;

public class EnderecoParser {

  public Endereco toEntity(EnderecoDTO dto) {
    Endereco endereco = new Endereco();
    endereco.setBairro(dto.getBairro());
    endereco.setLocalidade(dto.getLocalidade());
    endereco.setCep(dto.getCep());
    endereco.setComplemento(dto.getComplemento());
    endereco.setId(dto.getId());
    endereco.setIdPessoa(dto.getIdPessoa());
    endereco.setLogradouro(dto.getLogradouro());
    endereco.setLocalidade(dto.getLocalidade());
    return endereco;
  }

  public EnderecoDTO toDTO(Endereco entity) {
    EnderecoDTO enderecoDTO = new EnderecoDTO();
    enderecoDTO.setBairro(entity.getBairro());
    enderecoDTO.setLocalidade(entity.getLocalidade());
    enderecoDTO.setCep(entity.getCep());
    enderecoDTO.setComplemento(entity.getComplemento());
    enderecoDTO.setId(entity.getId());
    enderecoDTO.setIdPessoa(entity.getIdPessoa());
    enderecoDTO.setLogradouro(entity.getLogradouro());
    enderecoDTO.setLocalidade(entity.getLocalidade());
    return enderecoDTO;
  }
  
}
