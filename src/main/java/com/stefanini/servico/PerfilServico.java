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

import com.stefanini.dao.PerfilDAO;
import com.stefanini.dto.PerfilDTO;
import com.stefanini.model.Perfil;
import com.stefanini.parser.PerfilParser;

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
public class PerfilServico implements Serializable {
	
	/**
   * serial da classe
   */
  private static final long serialVersionUID = 1L;
  
  @Inject
	private PerfilDAO dao;
	
  @Inject
  private PerfilParser perfilParser; 
  
	/**
	 * Salvar os dados de um Perfil
	 */
  @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Perfil salvar(@Valid Perfil perfil) {
    if(dao.isNomeRepeated(perfil.getNome())) {
      return new Perfil();
    }
		return dao.salvar(perfil);
	}

	/**
	 * Atualizar o dados de um Perfil
	 */
  @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Perfil atualizar(@Valid Perfil perfil) {
    if(dao.isNomeRepeated(perfil.getNome())) {
      return new Perfil();
    }
		return dao.atualizar(perfil);
	}

	/**
	 * Remover um Perfil pelo id
	 */
  @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Boolean remover(Long id) {
    Optional<Perfil> perfil = dao.encontrar(id);
    if(perfil.isPresent()) {
      if(perfil.get().getPessoas().isEmpty()) {
        dao.remover(id);
        return true;
      }
    }
		return false;
	}

	/**
	 * Buscar uma lista de Perfil
	 */
	public Optional<List<Perfil>> getList() {
		return dao.getList();
	}

	/**
	 * Buscar um Perfil pelo ID
	 */
	public Optional<Perfil> encontrar(Long id) {
		return dao.encontrar(id);
	}

  public Optional<List<PerfilDTO>> encontrarPorNome(String nome) {
    Optional<List<Perfil>> optPerfil = dao.encontrarPorNome(nome);
    if(optPerfil.isPresent()) {
      return Optional.of(perfilParser.toDTOList(optPerfil.get()));
    }
    List<PerfilDTO> retorno = new ArrayList<PerfilDTO>();
    return Optional.of(retorno);
  }

}
