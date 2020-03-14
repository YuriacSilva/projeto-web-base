package com.stefanini.servico;

import java.io.Serializable;
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

import com.stefanini.dao.PessoaPerfilDAO;
import com.stefanini.dto.PessoaPerfilDTO;
import com.stefanini.parser.PessoaPerfilParser;

/**
 * 
 * Classe de servico
 * @author yuri araujo de castro silva
 *
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
@Transactional
public class PessoaPerfilServico implements Serializable {
	
	/**
   * serial da classe 
   */
  private static final long serialVersionUID = 1L;
  
  @Inject
	private PessoaPerfilDAO dao;
  
  @Inject
  private PessoaPerfilParser pessoaPerfilParser;
	
	/**
	 * Salvar os dados de uma Pessoa
	 */
  @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public PessoaPerfilDTO salvar(@Valid PessoaPerfilDTO pessoaPerfil) {
		return pessoaPerfilParser.toDTO(dao.salvar(pessoaPerfilParser.toEntity(pessoaPerfil)));
	}

	/**
	 * Atualizar o dados de uma pessoa
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public PessoaPerfilDTO atualizar(@Valid PessoaPerfilDTO entity) {
		return pessoaPerfilParser.toDTO(dao.atualizar(pessoaPerfilParser.toEntity(entity)));
	}

	/**
	 * Remover uma pessoa pelo id
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void remover(Long id) {
		dao.remover(id);		
	}

	/**
	 * Buscar uma lista de Pessoa
	 */
	public Optional<List<PessoaPerfilDTO>> getList() {
		return Optional.of(pessoaPerfilParser.toDTOList(dao.getList().get()));
	}

	/**
	 * Buscar uma Pessoa pelo ID
	 */
	public Optional<PessoaPerfilDTO> encontrar(Long id) {
		return Optional.of(pessoaPerfilParser.toDTO(dao.encontrar(id).get()));
	}

}
