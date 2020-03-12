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
	public Pessoa salvar(@Valid Pessoa pessoa) {
	  if(dao.encontrarPorEmail(pessoa.getEmail())) {
      return new Pessoa();
    }
    return dao.salvar(pessoa);
	}

	/**
	 * Atualizar o dados de uma pessoa
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Pessoa atualizar(@Valid Pessoa pessoa) {
	  if(dao.encontrarPorEmail(pessoa.getEmail())) {
	    return new Pessoa();
	  }
		return dao.atualizar(pessoa);
	}

	/**
	 * Remover uma pessoa pelo id
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Boolean remover(@Valid Long id) {
	  Optional<Pessoa> pessoa = dao.encontrar(id);
	  if(pessoa.isPresent()) {
	    System.out.println("isPresent");
  	  if(pessoa.get().getEnderecos().isEmpty()) {
  	    System.out.println("isEmpty");
  	    dao.remover(id);
  	    return true;
  	  }
  	  System.out.println("not isEmpty");
  	  return false;
	  }
	  System.out.println("not isPresent");
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
	
	public Optional<List<PessoaDTO>> encontrarPorFiltro(PessoaDTO pessoaDTO) {
    Optional<List<Pessoa>> optPessoa = dao.encontrarComFiltro(pessoaDTO);
    if(optPessoa.isPresent()) {
      return Optional.of(pessoaParser.toDTOList(optPessoa.get()));
    }
    List<PessoaDTO> retorno = new ArrayList<PessoaDTO>();
    return Optional.of(retorno);
  }
	
}
