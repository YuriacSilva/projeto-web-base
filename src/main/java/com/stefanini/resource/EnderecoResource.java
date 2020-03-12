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

import com.stefanini.dto.EnderecoDTO;
import com.stefanini.dto.PessoaDTO;
import com.stefanini.model.Endereco;
import com.stefanini.servico.EnderecoServico;

@Path("enderecos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EnderecoResource {

	@Inject
	private EnderecoServico enderecoServico;

	@GET
	public Response obterListaEndereco() {
		return Response.ok(enderecoServico.getList().get()).build();
	}

	@POST
	public Response salvarEndereco(@Valid Endereco endereco) {
		return (enderecoServico.salvar(endereco).toString().equals(endereco.toString()) ? 
		    Response.ok(enderecoServico.salvar(endereco)).build() :
		      Response.status(Status.BAD_REQUEST).entity("erro de regra de negócio").build());
	}
	
	@GET
	@Path("{id}")
	public Response obterEndereco(@PathParam("id") Long id) {
		return Response.ok(enderecoServico.encontrar(id).get()).build();
	}

	@PUT
  public Response atualizarEndereco(@Valid Endereco endereco) {
	  return (enderecoServico.atualizar(endereco).toString().equals(endereco.toString()) ? 
        Response.ok(enderecoServico.atualizar(endereco)).build() :
          Response.status(Status.BAD_REQUEST).entity("erro de regra de negócio").build());
  }
  
  @DELETE
  @Path("{id}")
  public void removerEndereco(@PathParam("id") Long id) {
    enderecoServico.remover(id);
    Response.ok().build();
  }
  
  @GET
  @Path("/pessoa/{id}")
  public Response obterEnderecoPorPessoa(@PathParam("id") Long id) {
    return Response.ok(enderecoServico.encontrarPorPessoa(id).get()).build();
  }

  @GET
  @Path("/filtrar")
  public Response obterListaEnderecoFiltro(
      @QueryParam("id") Long id, 
      @QueryParam("cep") String cep, 
      @QueryParam("uf") String uf,
      @QueryParam("localidade") String localidade,
      @QueryParam("bairro") String bairro,
      @QueryParam("complemento") String complemento,
      @QueryParam("logradouro") String logradouro,
      @QueryParam("idPessoa") Long idPessoa
      ) {
    
    EnderecoDTO enderecoDTO = new EnderecoDTO(id, cep, uf, localidade, bairro, complemento, logradouro, idPessoa);
    return Response.ok(enderecoServico.encontrarPorFiltro(enderecoDTO)).build();
  }
  
}
