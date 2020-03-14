package com.stefanini.servico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;

import com.stefanini.dao.PessoaDAO;
import com.stefanini.dto.PessoaDTO;
import com.stefanini.model.Pessoa;
import com.stefanini.parser.PessoaParser;

/**
 * 
 * Classe de servico, as regras de negocio devem estar nessa classe
 * 
 * @author joaopedromilhome
 *
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
@Transactional
public class PessoaServico implements Serializable {

	/**
	 * serial da classe
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private PessoaDAO dao;
	
	@Inject
	private PessoaParser pessoaParser;

	/**
	 * Salvar os dados de uma Pessoa
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public PessoaDTO salvar(@Valid PessoaDTO pessoa) {
	  if(dao.isEmailRepeated(pessoa.getEmail())) {
      return new PessoaDTO();
    }
	  PessoaDTO salvar = pessoaParser.toDTO(dao.salvar(pessoaParser.toEntity(pessoa)));
    return salvar;
	}

	/**
	 * Atualizar o dados de uma pessoa
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public PessoaDTO atualizar(@Valid PessoaDTO pessoa) {
	  if(dao.isEmailRepeated(pessoa.getEmail())) {
	    return new PessoaDTO();
	  }
	  PessoaDTO atualizar = pessoaParser.toDTO(dao.atualizar(pessoaParser.toEntity(pessoa)));
		return atualizar;
	}

	/**
	 * Remover uma pessoa pelo id
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Boolean remover(@Valid Long id) {
	  Optional<Pessoa> pessoa = dao.encontrar(id);
	  if(pessoa.isPresent()) {
  	  if(pessoa.get().getEnderecos().isEmpty()) {
  	    dao.remover(id);
  	    return true;
  	  }
	  }
	  return false;
	}

	/**
	 * Buscar uma lista de Pessoa
	 */
	public Optional<List<PessoaDTO>> getList() {
	  Optional<List<Pessoa>> optList = dao.getList();
	  if(optList.isPresent()) {
	    return Optional.of(pessoaParser.toDTOList(optList.get()));
	  }
		return Optional.of(new ArrayList<>());
	}

	/**
	 * Buscar uma Pessoa pelo ID
	 */
	public Optional<PessoaDTO> encontrar(Long id) {
	  Optional<Pessoa> optPessoa = dao.encontrar(id);
    if(optPessoa.isPresent()) {
      return Optional.of(pessoaParser.toDTO(optPessoa.get()));
    }
		return Optional.of(new PessoaDTO());
	}
	
	/**
   * Buscar uma Pessoa pelo nome
   */
  public Optional<List<PessoaDTO>> encontrarPorNome(String nome) {
    Optional<List<Pessoa>> optPessoa = dao.encontrarPorNome(nome);
    if(optPessoa.isPresent()) {
      return Optional.of(pessoaParser.toDTOList(optPessoa.get()));
    }
    List<PessoaDTO> retorno = new ArrayList<PessoaDTO>();
    return Optional.of(retorno);
  }
	
  /**
   * Buscar uma Pessoa pelo email
   */
  public Optional<PessoaDTO> encontrarPorEmail(String email) {
    Optional<Pessoa> optPessoa = dao.encontrarPorEmail(email);
    if(optPessoa.isPresent()) {
      return Optional.of(pessoaParser.toDTO(optPessoa.get()));
    }
    PessoaDTO retorno = new PessoaDTO();
    return Optional.of(retorno);
  }
  
  /**
   * Buscar uma Pessoa pela UF
   */
  public Optional<List<PessoaDTO>> encontrarPorUf(String uf) {
    Optional<List<Pessoa>> optPessoa = dao.encontrarPorUf(uf);
    if(optPessoa.isPresent()) {
      return Optional.of(pessoaParser.toDTOList(optPessoa.get()));
    }
    List<PessoaDTO> retorno = new ArrayList<PessoaDTO>();
    return Optional.of(retorno);
  }
  
  
  /**
   * Buscar uma Pessoa por qualquer um de seus atributos
   */
	public Optional<List<PessoaDTO>> encontrarPorFiltro(PessoaDTO pessoaDTO) {
    Optional<List<Pessoa>> optPessoa = dao.encontrarComFiltro(pessoaDTO);
    if(optPessoa.isPresent()) {
      return Optional.of(pessoaParser.toDTOList(optPessoa.get()));
    }
    List<PessoaDTO> retorno = new ArrayList<PessoaDTO>();
    return Optional.of(retorno);
  }
	
}
