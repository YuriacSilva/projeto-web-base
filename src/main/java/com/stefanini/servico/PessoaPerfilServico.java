package com.stefanini.servico;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.validation.Valid;

import com.stefanini.dao.PessoaPerfilDAO;
import com.stefanini.model.PessoaPerfil;
import com.stefanini.util.IGenericService;

/**
 * 
 * Classe de servico
 * @author yuri araujo de castro silva
 *
 */
public class PessoaPerfilServico implements IGenericService<PessoaPerfil, Long> {
	
	@Inject
	private PessoaPerfilDAO dao;
	
	/**
	 * Salvar os dados de uma Pessoa
	 */
	public PessoaPerfil salvar(@Valid PessoaPerfil pessoaPerfil) {
		return dao.salvar(pessoaPerfil);
	}

	/**
	 * Atualizar o dados de uma pessoa
	 */
	@Override
	public PessoaPerfil atualizar(@Valid PessoaPerfil entity) {
		return dao.atualizar(entity);
	}

	/**
	 * Remover uma pessoa pelo id
	 */
	@Override
	public void remover(Long id) {
		dao.remover(id);		
	}

	/**
	 * Buscar uma lista de Pessoa
	 */
	@Override
	public Optional<List<PessoaPerfil>> getList() {
		return dao.getList();
	}

	/**
	 * Buscar uma Pessoa pelo ID
	 */
	@Override
	public Optional<PessoaPerfil> encontrar(Long id) {
		return dao.encontrar(id);
	}

}
