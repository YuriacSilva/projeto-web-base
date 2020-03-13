package com.stefanini.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import com.stefanini.dao.abstracao.GenericDao;
import com.stefanini.model.Perfil;

/**
 * O Unico objetivo da Dao 
 * @author yuri araujo de castro silva
 *
 */
public class PerfilDAO extends GenericDao<Perfil, Long> {

	public PerfilDAO() {
		super(Perfil.class);
	}

  public Optional<List<Perfil>> encontrarPorNome(String nome) {
    CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();
    CriteriaQuery<Perfil> cq = cb.createQuery(Perfil.class);
    Root<Perfil> root = cq.from(Perfil.class);
    cq.select(root);
    ParameterExpression<String> pe = cb.parameter(String.class);
    cq.where(cb.equal(root.get("nome"), nome.toUpperCase()));
    TypedQuery<Perfil> query = this.getEntityManager().createQuery(cq);
    
    return Optional.of(query.getResultList());
  }

  /**
   * Metodo retorna true se nome de perfil ja existir na base de dados
   */
  public Boolean isNomeRepeated(String nome) {
    CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();
    CriteriaQuery<Perfil> cq = cb.createQuery(Perfil.class);
    Root<Perfil> root = cq.from(Perfil.class);
    cq.select(root);
    ParameterExpression<String> pe = cb.parameter(String.class);
    cq.where(cb.equal(root.get("nome"), nome.toUpperCase()));
    TypedQuery<Perfil> query = this.getEntityManager().createQuery(cq);
    
    return !query.getResultList().isEmpty();
  }
  
}
