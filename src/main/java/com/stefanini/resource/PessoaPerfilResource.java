package com.stefanini.resource;

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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.stefanini.dto.PessoaPerfilDTO;
import com.stefanini.servico.PessoaPerfilServico;

@Path("pessoasperfis")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PessoaPerfilResource {

	@Inject
	private PessoaPerfilServico pessoaPerfilServico;

	@GET
	public Response obterListaPessoaPerfil() {
		return Response.ok(pessoaPerfilServico.getList().get()).build();
	}

  @GET
  @Path("{id}")
  public Response obterPessoaPerfilPorId(@PathParam("id") Long id) {
    return Response.ok(pessoaPerfilServico.encontrar(id).get()).build();
  }
  
	@POST
	public Response salvarPessoaPerfil(@Valid PessoaPerfilDTO pessoaPerfil) {
		return Response.ok(pessoaPerfilServico.salvar(pessoaPerfil)).build();
	}
	
	@PUT
  public Response atualizarPessoaPerfil(@Valid PessoaPerfilDTO pessoaPerfil) {
	  return Response.ok(pessoaPerfilServico.atualizar(pessoaPerfil)).build();
  }
  
  @DELETE
  @Path("{id}")
  public Response removerPerfil(@PathParam("id") Long id) {
    pessoaPerfilServico.remover(id); 
    return Response.ok().build();
  }
	
}
