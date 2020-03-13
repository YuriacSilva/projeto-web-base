package com.stefanini.dao;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import com.stefanini.dao.abstracao.GenericDao;
import com.stefanini.dto.EnderecoDTO;
import com.stefanini.model.Endereco;

/**
 * O Unico objetivo da Dao 
 * @author joaopedromilhome
 *
 */
public class EnderecoDAO extends GenericDao<Endereco, Long> {

	public EnderecoDAO() {
		super(Endereco.class);
	}

	/**
   * Metodo retorna lista de enderecos por ID de Pessoa
   */
  public Optional<List<Endereco>> encontrarPorPessoa(Long idPessoa) {
    CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();
    CriteriaQuery<Endereco> cq = cb.createQuery(Endereco.class);
    Root<Endereco> root = cq.from(Endereco.class);
    cq.select(root);
    ParameterExpression<String> pe = cb.parameter(String.class);
    cq.where(cb.equal(root.get("idPessoa"), idPessoa));
    TypedQuery<Endereco> query = this.getEntityManager().createQuery(cq);
    
    return Optional.of(query.getResultList());
  }
//  
//  public Optional<List<Endereco>> encontrarPorPessoa(Long idPessoa){
//    StringBuilder sql = new StringBuilder();
//    sql.append("SELECT e FROM Endereco e WHERE e.idPessoa = :idPessoa");
//    
//    TypedQuery<Endereco> query = getEntityManager().createQuery(sql.toString(), Endereco.class);
//    
//    query.setParameter("idPessoa", idPessoa);
//    
//    return Optional.of(query.getResultList());
//  }
	
	public Optional<List<Endereco>> encontrarPorFiltro(EnderecoDTO filtro) {
    
	  Long id = filtro.getId();
    String cep = filtro.getCep();
    String uf = filtro.getUf();
    String localidade = filtro.getLocalidade();
    String bairro = filtro.getBairro();
    String complemento = filtro.getComplemento();
    String logradouro = filtro.getLogradouro();
    Long idPessoa = filtro.getIdPessoa();
    
    Boolean flag = false;

    StringBuilder sql = new StringBuilder();
    sql.append("SELECT e FROM Endereco e WHERE");

    if (Objects.nonNull(id)) {
      sql.append(" e.id = :id");
      flag = true;
    }
    if (Objects.nonNull(cep)) {
      if (flag) {
        sql.append(" AND");
      }
      sql.append(" e.cep = :cep");
      flag = true;
    }
    if (Objects.nonNull(uf)) {
      if (flag) {
        sql.append(" AND");
      }
      sql.append(" e.uf = :uf");
      flag = true;
    }
    if (Objects.nonNull(localidade)) {
      if (flag) {
        sql.append(" AND");
      }
      sql.append(" e.localidade = :localidade");
      flag = true;
    }
    if (Objects.nonNull(bairro)) {
      if (flag) {
        sql.append(" AND");
      }
      sql.append(" e.bairro = :bairro");
      flag = true;
    }
    if (Objects.nonNull(complemento)) {
      if (flag) {
        sql.append(" AND");
      }
      sql.append(" e.complemento = :complemento");
      flag = true;
    }
    if (Objects.nonNull(logradouro)) {
      if (flag) {
        sql.append(" AND");
      }
      sql.append(" e.logradouro = :logradouro");
      flag = true;
    }
    if (Objects.nonNull(idPessoa)) {
      if (flag) {
        sql.append(" AND");
      }
      sql.append(" e.idPessoa = :idPessoa");
    }

    TypedQuery<Endereco> query = getEntityManager().createQuery(sql.toString(), Endereco.class);

    if (Objects.nonNull(id)) {
      query.setParameter("id", id);
    }
    if (Objects.nonNull(cep)) {
      query.setParameter("cep", cep);
    }
    if (Objects.nonNull(uf)) {
      query.setParameter("uf", uf);
    }
    if (Objects.nonNull(localidade)) {
      query.setParameter("localidade", localidade);
    }
    if (Objects.nonNull(bairro)) {
      query.setParameter("bairro", bairro);
    }
    if (Objects.nonNull(complemento)) {
      query.setParameter("complemento", complemento);
    }
    if (Objects.nonNull(logradouro)) {
      query.setParameter("logradouro", logradouro);
    }
    if (Objects.nonNull(idPessoa)) {
      query.setParameter("idPessoa", idPessoa);
    }

    return Optional.of(query.getResultList());
  }
	
}
