package com.mitocode.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mitocode.dto.TipoVacunaDTO;
import com.mitocode.exception.ModeloNotFoundException;
import com.mitocode.model.TipoVacuna;
import com.mitocode.service.ITipoVacunaService;

@RestController
@RequestMapping("/tipovacuna")
public class TipoVacunaController {

	@Autowired
	private ITipoVacunaService service;
	
	@Autowired
	private ModelMapper mapper;
	
	@PreAuthorize("@authServiceImpl.tieneAcceso('listar')")
	@GetMapping
	public ResponseEntity<List<TipoVacunaDTO>> listar() throws Exception{		
		List<TipoVacunaDTO> lista = service.listar().stream().map(p -> mapper.map(p, TipoVacunaDTO.class)).collect(Collectors.toList());
		return new ResponseEntity<>(lista, HttpStatus.OK);
	}
	
	@PreAuthorize("@authServiceImpl.tieneAcceso('listarporid')")
	@GetMapping("/{id}")
	public ResponseEntity<TipoVacunaDTO> listarPorId(@PathVariable("id") Integer id) throws Exception{
		TipoVacuna obj = service.listarPorId(id);
		
		if(obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}
		
		TipoVacunaDTO dto = mapper.map(obj, TipoVacunaDTO.class);
		
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@PreAuthorize("@authServiceImpl.tieneAcceso('registrar')")
	@PostMapping
	public ResponseEntity<Void> registrar(@Valid @RequestBody TipoVacunaDTO dtoRequest) throws Exception{
		TipoVacuna tva = mapper.map(dtoRequest, TipoVacuna.class);
		TipoVacuna obj = service.registrar(tva);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdTipoVacuna()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PreAuthorize("@authServiceImpl.tieneAcceso('modificaradmin')")
	@PutMapping
	public ResponseEntity<TipoVacuna> modificar(@Valid @RequestBody TipoVacunaDTO dtoRequest) throws Exception{
		TipoVacuna obj = service.listarPorId(dtoRequest.getIdTipoVacuna());
		
		if(obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + dtoRequest.getIdTipoVacuna());
		}
		
		TipoVacuna tva = mapper.map(dtoRequest, TipoVacuna.class);
		TipoVacuna tipvac = service.modificar(tva);		
		
		return new ResponseEntity<>(tipvac, HttpStatus.OK);
	}
	
	@PreAuthorize("@authServiceImpl.tieneAcceso('eliminar')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception{
		TipoVacuna obj = service.listarPorId(id);
		
		if(obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}
		
		service.eliminar(id);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PreAuthorize("@authServiceImpl.tieneAcceso('hateoas')")
	@GetMapping("/hateoas/{id}")
	public EntityModel<TipoVacunaDTO> listarHateoas(@PathVariable("id") Integer id) throws Exception{
		TipoVacuna obj = service.listarPorId(id);
		if(obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}		
		TipoVacunaDTO dto = mapper.map(obj, TipoVacunaDTO.class);

		EntityModel<TipoVacunaDTO> recurso = EntityModel.of(dto);

		WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).listarPorId(id));
		WebMvcLinkBuilder link2 = linkTo(methodOn(this.getClass()).listarPorId(id));
		recurso.add(link1.withRel("tipovacu-info1"));
		recurso.add(link2.withRel("tipovacu-info2"));
		
		return recurso;
		
	}
	
}
