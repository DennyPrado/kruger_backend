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

import com.mitocode.dto.EstadoVacunacionDTO;
import com.mitocode.exception.ModeloNotFoundException;
import com.mitocode.model.EstadoVacunacion;
import com.mitocode.service.IEstadoVacunacionService;

@RestController
@RequestMapping("/estadovacunacion")
public class EstadoVacunacionController {

	@Autowired
	private IEstadoVacunacionService service;
	
	@Autowired
	private ModelMapper mapper;
	
	@PreAuthorize("@authServiceImpl.tieneAcceso('listar')")
	@GetMapping
	public ResponseEntity<List<EstadoVacunacionDTO>> listar() throws Exception{		
		List<EstadoVacunacionDTO> lista = service.listar().stream().map(p -> mapper.map(p, EstadoVacunacionDTO.class)).collect(Collectors.toList());
		return new ResponseEntity<>(lista, HttpStatus.OK);
	}
	
	@PreAuthorize("@authServiceImpl.tieneAcceso('listarporid')")
	@GetMapping("/{id}")
	public ResponseEntity<EstadoVacunacionDTO> listarPorId(@PathVariable("id") Integer id) throws Exception{
		EstadoVacunacion obj = service.listarPorId(id);
		
		if(obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}
		
		EstadoVacunacionDTO dto = mapper.map(obj, EstadoVacunacionDTO.class);
		
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@PreAuthorize("@authServiceImpl.tieneAcceso('registrar')")
	@PostMapping
	public ResponseEntity<Void> registrar(@Valid @RequestBody EstadoVacunacionDTO dtoRequest) throws Exception{
		EstadoVacunacion esv = mapper.map(dtoRequest, EstadoVacunacion.class);
		EstadoVacunacion obj = service.registrar(esv);		
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdEstadoVacunacion()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PreAuthorize("@authServiceImpl.tieneAcceso('modificaradmin')")
	@PutMapping
	public ResponseEntity<EstadoVacunacion> modificar(@Valid @RequestBody EstadoVacunacionDTO dtoRequest) throws Exception{
		EstadoVacunacion obj = service.listarPorId(dtoRequest.getIdEstadoVacunacion());
		
		if(obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + dtoRequest.getIdEstadoVacunacion());
		}
		
		EstadoVacunacion esv = mapper.map(dtoRequest, EstadoVacunacion.class);
		EstadoVacunacion estado = service.modificar(esv);		
		
		return new ResponseEntity<>(estado, HttpStatus.OK);
	}
	
	@PreAuthorize("@authServiceImpl.tieneAcceso('eliminar')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception{
		EstadoVacunacion obj = service.listarPorId(id);
		
		if(obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}
		
		service.eliminar(id);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PreAuthorize("@authServiceImpl.tieneAcceso('hateoas')")
	@GetMapping("/hateoas/{id}")
	public EntityModel<EstadoVacunacionDTO> listarHateoas(@PathVariable("id") Integer id) throws Exception{
		EstadoVacunacion obj = service.listarPorId(id);
		if(obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}		
		EstadoVacunacionDTO dto = mapper.map(obj, EstadoVacunacionDTO.class);

		EntityModel<EstadoVacunacionDTO> recurso = EntityModel.of(dto);
		
		WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).listarPorId(id));
		WebMvcLinkBuilder link2 = linkTo(methodOn(this.getClass()).listarPorId(id));
		recurso.add(link1.withRel("estadovacu-info1"));
		recurso.add(link2.withRel("estadovacu-info2"));
		
		return recurso;
		
	}
	
}
