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
import com.stefanini.parser.EnderecoParser;

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
  
  @Inject
  private EnderecoParser enderecoParser;

  @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public EnderecoDTO salvar(@Valid EnderecoDTO entity) {
		return enderecoParser.toDTO(dao.salvar(enderecoParser.toEntity(entity)));
	}

  @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public EnderecoDTO atualizar(@Valid EnderecoDTO entity) {
		return enderecoParser.toDTO(dao.atualizar(enderecoParser.toEntity(entity)));
	}

  @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void remover(Long id) {
	  dao.remover(id);
	}

	public Optional<List<EnderecoDTO>> getList() {
		return Optional.of(enderecoParser.toDTOList(dao.getList().get()));
	}

	public Optional<EnderecoDTO> encontrar(Long id) {
		return Optional.of(enderecoParser.toDTO(dao.encontrar(id).get()));
	}
	
	public Optional<List<EnderecoDTO>> encontrarPorPessoa(Long idPessoa) {
    return Optional.of(enderecoParser.toDTOList(dao.encontrarPorPessoa(idPessoa).get()));
  }
	
	public Optional<List<EnderecoDTO>> encontrarPorFiltro(EnderecoDTO enderecoDTO) {
    return Optional.of(enderecoParser.toDTOList(dao.encontrarPorFiltro(enderecoDTO).get()));
  }
}
