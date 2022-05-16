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

import com.mitocode.dto.RolDTO;
import com.mitocode.exception.ModeloNotFoundException;
import com.mitocode.model.Rol;
import com.mitocode.service.IRolService;

@RestController
@RequestMapping("/roles")
public class RolController {

	@Autowired
	private IRolService service;
	
	@Autowired
	private ModelMapper mapper;
	
	@PreAuthorize("@authServiceImpl.tieneAcceso('listar')")
	@GetMapping
	public ResponseEntity<List<RolDTO>> listar() throws Exception{		
		List<RolDTO> lista = service.listar().stream().map(p -> mapper.map(p, RolDTO.class)).collect(Collectors.toList());
		return new ResponseEntity<>(lista, HttpStatus.OK);
	}
	
	@PreAuthorize("@authServiceImpl.tieneAcceso('listarporid')")
	@GetMapping("/{id}")
	public ResponseEntity<RolDTO> listarPorId(@PathVariable("id") Integer id) throws Exception{
		Rol obj = service.listarPorId(id);
		
		if(obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}
		
		RolDTO dto = mapper.map(obj, RolDTO.class);
		
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@PreAuthorize("@authServiceImpl.tieneAcceso('registrar')")
	@PostMapping
	public ResponseEntity<Void> registrar(@Valid @RequestBody RolDTO dtoRequest) throws Exception{
		Rol rl = mapper.map(dtoRequest, Rol.class);
		Rol obj = service.registrar(rl);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdRol()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PreAuthorize("@authServiceImpl.tieneAcceso('modificaradmin')")
	@PutMapping
	public ResponseEntity<Rol> modificar(@Valid @RequestBody RolDTO dtoRequest) throws Exception{
		Rol obj = service.listarPorId(dtoRequest.getIdRol());
		
		if(obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + dtoRequest.getIdRol());
		}
		
		Rol rol = mapper.map(dtoRequest, Rol.class);
		Rol trl = service.modificar(rol);		
		
		return new ResponseEntity<>(trl, HttpStatus.OK);
	}
	
	@PreAuthorize("@authServiceImpl.tieneAcceso('eliminar')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception{
		Rol obj = service.listarPorId(id);
		
		if(obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}
		
		service.eliminar(id);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PreAuthorize("@authServiceImpl.tieneAcceso('hateoas')")
	@GetMapping("/hateoas/{id}")
	public EntityModel<RolDTO> listarHateoas(@PathVariable("id") Integer id) throws Exception{
		Rol obj = service.listarPorId(id);
		if(obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}		
		RolDTO dto = mapper.map(obj, RolDTO.class);

		EntityModel<RolDTO> recurso = EntityModel.of(dto);

		WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).listarPorId(id));
		WebMvcLinkBuilder link2 = linkTo(methodOn(this.getClass()).listarPorId(id));
		recurso.add(link1.withRel("rol-info1"));
		recurso.add(link2.withRel("rol-info2"));
		
		return recurso;
		
	}
	
}
