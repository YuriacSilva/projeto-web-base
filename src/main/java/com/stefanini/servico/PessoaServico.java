package com.stefanini.servico;

import com.stefanini.dao.PessoaDao;
import com.stefanini.dto.PessoaDTO;
import com.stefanini.model.Pessoa;
import com.stefanini.parser.PessoaParser;
import com.stefanini.util.IGenericService;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private PessoaDao dao;
	
	@Inject
	private PessoaParser pessoaParser;

	/**
	 * Salvar os dados de uma Pessoa
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Pessoa salvar(@Valid Pessoa pessoa) {
		return dao.salvar(pessoa);
	}

	/**
	 * Atualizar o dados de uma pessoa
	 */
//	@Override
	public Pessoa atualizar(@Valid Pessoa entity) {
		return dao.atualizar(entity);
	}

	/**
	 * Remover uma pessoa pelo id
	 */
//	@Override
	public void remover(@Valid Long id) {
		dao.remover(id);
	}

	/**
	 * Buscar uma lista de Pessoa
	 */
//	@Override
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
//	@Override
	public Optional<PessoaDTO> encontrar(Long id) {
	  Optional<Pessoa> optPessoa = dao.encontrar(id);
    if(optPessoa.isPresent()) {
      return Optional.of(pessoaParser.toDTO(optPessoa.get()));
    }
		return Optional.of(new PessoaDTO());
	}

}
