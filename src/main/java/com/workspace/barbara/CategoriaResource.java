package com.workspace.barbara;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.yaml.snakeyaml.tokens.DocumentEndToken;

import io.quarkus.hibernate.orm.panache.Panache;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Path("/categoria")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CategoriaResource {

	@Inject
	CategoriaRepository categoriaRepository;

	@GET
	public List<Categoria> listarCategorias() {
		return categoriaRepository.findAll().list();
	}

	@POST
	@Transactional
	public Categoria salvarCategoria(Categoria categoria) {
		categoriaRepository.persist(categoria);
		return categoria;
	}

	@PUT
	@Transactional
	@Path("/{id}")
	public Categoria alterarCategoria(@PathParam("id") Long id, CategoriaDTO dto) {
		Optional<Categoria> optional = categoriaRepository.findByIdOptional(id);
		if (optional.isEmpty()) {
			throw new NotFoundException();
		}
		Categoria categoria = optional.get();
		categoria.nome = dto.nome;
		categoriaRepository.update(categoria);
		return categoria;

	}

	@DELETE
	@Path("/{id}")
	@Transactional
	public void deletarCategoria(@PathParam("id") Long id) {
		Optional<Categoria> optional = categoriaRepository.findByIdOptional(id);
		if (optional.isEmpty()) {
			throw new NotFoundException();
		}
		Categoria categoria = optional.get();
		categoriaRepository.delete(categoria);
	}

}
