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
import javax.validation.Valid;

import com.stefanini.dao.EnderecoDAO;
import com.stefanini.dto.EnderecoDTO;
import com.stefanini.model.Endereco;

/**
 * 
 * Classe de servico, as regras de negocio devem estar nessa classe
 * @author joaopedromilhome
 *
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class EnderecoServico implements Serializable {
	
	/**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  @Inject
	private EnderecoDAO dao;

  @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Endereco salvar(@Valid Endereco entity) {
		return dao.salvar(entity);
	}

  @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Endereco atualizar(@Valid Endereco entity) {
		return dao.atualizar(entity);
	}

  @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void remover(Long id) {
	  dao.remover(id);
	}

	public Optional<List<Endereco>> getList() {
		return Optional.of(dao.getList().get());
	}

	public Optional<Endereco> encontrar(Long id) {
		return dao.encontrar(id);
	}
	
	public Optional<List<Endereco>> encontrarPorPessoa(Long idPessoa) {
    return dao.encontrarPorPessoa(idPessoa);
  }
	
	public Optional<List<Endereco>> encontrarPorFiltro(EnderecoDTO enderecoDTO) {
    return dao.encontrarPorFiltro(enderecoDTO);
  }
}
