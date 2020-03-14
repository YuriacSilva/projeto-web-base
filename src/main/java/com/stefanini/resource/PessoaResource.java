package com.stefanini.resource;

import java.util.Objects;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.stefanini.dto.PessoaDTO;
import com.stefanini.servico.PessoaServico;

@Path("pessoas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PessoaResource {

	@Inject
	private PessoaServico pessoaServico;

	@GET
	public Response obterListaPessoa() {
		return Response.ok(pessoaServico.getList().get()).build();
	}

  @GET
  @Path("{id}")
  public Response obterPessoaPorId(@PathParam("id") Long id) {
    return Response.ok(pessoaServico.encontrar(id).get()).build();
  }
  
  @GET
  @Path("/buscarpornome")
  public Response obterPessoaPorNome(@QueryParam("nome") String nome) {
    return Response.ok(pessoaServico.encontrarPorNome(nome).get()).build();
  }
  
  @GET
  @Path("/buscarporuf")
  public Response obterPessoaPorUf(@QueryParam("uf") String uf) {
    return Response.ok(pessoaServico.encontrarPorUf(uf).get()).build();
  }
  
  @GET
  @Path("/filtrar")
  public Response obterListaPessoaFiltro(
      @QueryParam("nome") String nome, 
      @QueryParam("email") String email, 
      @QueryParam("situacao") String situacao
      ) {
    
    PessoaDTO pessoaDTO = new PessoaDTO(nome, email, null, (Objects.nonNull(situacao) ? Boolean.valueOf(situacao) : null));
    return Response.ok(pessoaServico.encontrarPorFiltro(pessoaDTO)).build();
  }
  
	@POST
	public Response salvarPessoa(@Valid PessoaDTO pessoa) {
	  PessoaDTO salvar = pessoaServico.salvar(pessoa);
	  boolean equals = Objects.nonNull(salvar.getEmail());
		return (equals ? 
		    Response.ok(salvar).build() :
		      Response.status(Status.BAD_REQUEST).entity("erro de regra de negócio").build());
	}
	
	@PUT
  public Response atualizarPessoa(@Valid PessoaDTO pessoa) {
	  PessoaDTO atualizar = pessoaServico.atualizar(pessoa);
	  boolean equals = Objects.nonNull(atualizar.getEmail());
	  return (equals ? 
        Response.ok(atualizar).build() :
          Response.status(Status.BAD_REQUEST).entity("erro de regra de negócio").build());
  }
  
  @DELETE
  @Path("{id}")
  public Response removerPessoa(@PathParam("id") Long id) {
    return (pessoaServico.remover(id) ? 
        Response.ok().build() : 
          Response.status(Status.BAD_REQUEST).entity("erro de regra de negócio").build());
  }
	
}
