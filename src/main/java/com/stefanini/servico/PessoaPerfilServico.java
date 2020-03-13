package com.stefanini.servico;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.validation.Valid;

import com.stefanini.dao.PessoaPerfilDAO;
import com.stefanini.model.PessoaPerfil;

/**
 * 
 * Classe de servico
 * @author yuri araujo de castro silva
 *
 */
public class PessoaPerfilServico implements Serializable {
	
	/**
   * serial da classe 
   */
  private static final long serialVersionUID = 1L;
  
  @Inject
	private PessoaPerfilDAO dao;
	
	/**
	 * Salvar os dados de uma Pessoa
	 */
  @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public PessoaPerfil salvar(@Valid PessoaPerfil pessoaPerfil) {
		return dao.salvar(pessoaPerfil);
	}

	/**
	 * Atualizar o dados de uma pessoa
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public PessoaPerfil atualizar(@Valid PessoaPerfil entity) {
		return dao.atualizar(entity);
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
	public Optional<List<PessoaPerfil>> getList() {
		return dao.getList();
	}

	/**
	 * Buscar uma Pessoa pelo ID
	 */
	public Optional<PessoaPerfil> encontrar(Long id) {
		return dao.encontrar(id);
	}

}
