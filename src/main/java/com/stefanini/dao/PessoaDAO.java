package com.stefanini.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.stefanini.dao.abstracao.GenericDao;
import com.stefanini.dto.PessoaDTO;
import com.stefanini.model.Endereco;
import com.stefanini.model.Pessoa;

/**
 * O Unico objetivo da Dao
 * 
 * @author joaopedromilhome
 *
 */
public class PessoaDAO extends GenericDao<Pessoa, Long> {

  public PessoaDAO() {
    super(Pessoa.class);
  }
  
  
  /**
   * metodo retorna uma Pessoa bucasndo pelo e-mail
   * @param email
   * @return Optional<Pessoa>
   */
  public Optional<Pessoa> encontrarPorEmail(String email) {
    CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();
    CriteriaQuery<Pessoa> cq = cb.createQuery(Pessoa.class);
    Root<Pessoa> root = cq.from(Pessoa.class);
    cq.select(root);
    ParameterExpression<String> pe = cb.parameter(String.class);
    cq.where(cb.equal(root.get("email"), email));
    TypedQuery<Pessoa> query = this.getEntityManager().createQuery(cq);
    
    if(query.getFirstResult()==0) {
      return Optional.of(new Pessoa());
    }
    return Optional.of(query.getSingleResult());
  }
  
  /**
   * Metodo retorna true se e-mail ja existir na base de dados
   */
  public Boolean isEmailRepeated(String email) {
    CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();
    CriteriaQuery<Pessoa> cq = cb.createQuery(Pessoa.class);
    Root<Pessoa> root = cq.from(Pessoa.class);
    cq.select(root);
    ParameterExpression<String> pe = cb.parameter(String.class);
    cq.where(cb.equal(root.get("email"), email));
    TypedQuery<Pessoa> query = this.getEntityManager().createQuery(cq);
    List<Pessoa> lista = query.getResultList();
    
    return !query.getResultList().isEmpty();
  }
//  public Boolean encontrarPorEmail(String email) {
//    StringBuilder sql = new StringBuilder();
//    sql.append("SELECT p FROM Pessoa p WHERE p.email LIKE :email");
//
//    TypedQuery<Pessoa> query = getEntityManager().createQuery(sql.toString(), Pessoa.class);
//
//    query.setParameter("email", email);
//
//    return !query.getResultList().isEmpty();
//  }

  public Optional<List<Pessoa>> encontrarPorNome(String nome) {
    CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();
    CriteriaQuery<Pessoa> cq = cb.createQuery(Pessoa.class);
    Root<Pessoa> root = cq.from(Pessoa.class);
    cq.select(root);
    ParameterExpression<String> pe = cb.parameter(String.class);
    cq.where(cb.equal(root.get("nome"), nome.toUpperCase()));
    TypedQuery<Pessoa> query = this.getEntityManager().createQuery(cq);
    
    return Optional.of(query.getResultList());
  }
  
  public Optional<List<Pessoa>> encontrarPorUf(String uf) {
    CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();
    CriteriaQuery<Pessoa> cq = cb.createQuery(Pessoa.class);
    Root<Pessoa> root = cq.from(Pessoa.class);
    cq.select(root);
    Join<Pessoa, Endereco> join = root.join("enderecos");
    cq.where(cb.equal(join.get("uf"), uf));
    ParameterExpression<String> pe = cb.parameter(String.class);
    TypedQuery<Pessoa> query = this.getEntityManager().createQuery(cq);
    
    return Optional.of(query.getResultList());
  }
  
  public Optional<List<Pessoa>> encontrarComFiltro(PessoaDTO filtro) {
    String nome = filtro.getNome();
    String email = filtro.getEmail();
    LocalDate dataNascimento = filtro.getDataNascimento();
    Boolean situacao = filtro.getSituacao();
    Boolean flag = false;

    StringBuilder sql = new StringBuilder();
    sql.append("SELECT p FROM Pessoa p WHERE");

    if (Objects.nonNull(nome)) {
      sql.append(" p.nome LIKE :nome");
      flag = true;
    }
    if (Objects.nonNull(email)) {
      if (flag) {
        sql.append(" AND");
      }
      sql.append(" p.email LIKE :email");
      flag = true;
    }
    if (Objects.nonNull(dataNascimento)) {
      if (flag) {
        sql.append(" AND");
      }
      sql.append(" p.dataNascimento = :dataNascimento");
      flag = true;
    }
    if (Objects.nonNull(situacao)) {
      if (flag) {
        sql.append(" AND");
      }
      sql.append(" p.situacao = :situacao");
    }

    TypedQuery<Pessoa> query = getEntityManager().createQuery(sql.toString(), Pessoa.class);

    if (Objects.nonNull(nome)) {
      query.setParameter("nome", nome);
    }
    if (Objects.nonNull(email)) {
      query.setParameter("email", email);
    }
    if (Objects.nonNull(dataNascimento)) {
      query.setParameter("dataNascimento", dataNascimento);
    }
    if (Objects.nonNull(situacao)) {
      query.setParameter("situacao", situacao);
    }

    return Optional.of(query.getResultList());
  }

}
