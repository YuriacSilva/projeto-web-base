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

import com.stefanini.dto.PerfilDTO;
import com.stefanini.servico.PerfilServico;

@Path("perfis")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PerfilResource {

	@Inject
	private PerfilServico perfilServico;
	
	@GET
	public Response obterListaPerfil() {
		return Response.ok(perfilServico.getList().get()).build();
	}

  @GET
  @Path("{id}")
  public Response obterPerfilPorId(@PathParam("id") Long id) {
    return Response.ok(perfilServico.encontrar(id).get()).build();
  }
  
  @GET
  @Path("/buscarpornome")
  public Response obterPerfilPorNome(@QueryParam("nome") String nome) {
    return Response.ok(perfilServico.encontrarPorNome(nome).get()).build();
  }
  
//  @GET
//  @Path("/filtrar")
//  public Response obterListaPerfilFiltro(
//      @QueryParam("nome") String nome, 
//      @QueryParam("descricao") String descricao, 
//      @QueryParam("dthrinclusao") LocalDateTime dataHoraInclusao,
//      @QueryParam("dthralteracao") LocalDateTime dataHoraAlteracao
//      ) {
//    
//    PerfilDTO perfilDTO = new PerfilDTO(nome, descricao, dataHoraInclusao, dataHoraAlteracao);
//    return Response.ok(perfilServico.encontrarPorFiltro(perfilDTO)).build();
//  }
  
	@POST
	public Response salvarPerfil(@Valid PerfilDTO perfil) {
	  PerfilDTO salvar = perfilServico.salvar(perfil);
    boolean equals = Objects.nonNull(salvar.getNome());
    return (equals ? 
        Response.ok(salvar).build() :
          Response.status(Status.BAD_REQUEST).entity("erro de regra de negócio").build());
	}
	
	@PUT
  public Response atualizarPerfil(@Valid PerfilDTO perfil) {
	  PerfilDTO atualizar = perfilServico.atualizar(perfil);
    boolean equals = Objects.nonNull(atualizar.getNome());
    return (equals ? 
        Response.ok(atualizar).build() :
          Response.status(Status.BAD_REQUEST).entity("erro de regra de negócio").build());
  }
  
  @DELETE
  @Path("{id}")
  public Response removerPerfil(@PathParam("id") Long id) {
    return (perfilServico.remover(id) ? 
        Response.ok().build() : 
          Response.status(Status.BAD_REQUEST).entity("erro de regra de negócio").build());
  }
	
}
