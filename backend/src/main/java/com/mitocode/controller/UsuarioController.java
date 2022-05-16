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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mitocode.dto.UsuarioDTO;
import com.mitocode.exception.ModeloNotFoundException;
import com.mitocode.model.Usuario;
import com.mitocode.service.IUsuarioService;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private IUsuarioService service;
	
	@Autowired
	private ModelMapper mapper;
	
	//@PreAuthorize("@authServiceImpl.tieneAcceso('listar')")
	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> listar() throws Exception{		
		List<UsuarioDTO> lista = service.listar().stream().map(p -> mapper.map(p, UsuarioDTO.class)).collect(Collectors.toList());
		return new ResponseEntity<>(lista, HttpStatus.OK);
	}
	
	//@PreAuthorize("@authServiceImpl.tieneAcceso('listarporid')")
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDTO> listarPorId(@PathVariable("id") Integer id) throws Exception{
		Usuario obj = service.listarPorId(id);
		
		if(obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}
		
		UsuarioDTO dto = mapper.map(obj, UsuarioDTO.class);
		
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	//@PreAuthorize("@authServiceImpl.tieneAcceso('registrar')")
	@PostMapping
	public ResponseEntity<Void> registrar(@Valid @RequestBody UsuarioDTO dtoRequest) throws Exception{
		Usuario esv = mapper.map(dtoRequest, Usuario.class);
		Usuario obj = service.registrarTransaccional(esv);		
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdUsuario()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	//@PreAuthorize("@authServiceImpl.tieneAcceso('modificaradmin')")
	@PutMapping
	public ResponseEntity<Usuario> modificar(@Valid @RequestBody UsuarioDTO dtoRequest) throws Exception{
		Usuario obj = service.listarPorId(dtoRequest.getIdUsuario());
		
		if(obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + dtoRequest.getIdUsuario());
		}
		
		Usuario esv = mapper.map(dtoRequest, Usuario.class);
		Usuario estado = service.modificar(esv);		
		
		return new ResponseEntity<>(estado, HttpStatus.OK);
	}
	
	//@PreAuthorize("@authServiceImpl.tieneAcceso('eliminar')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception{
		Usuario obj = service.listarPorId(id);
		
		if(obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}
		
		service.eliminar(id);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	//@PreAuthorize("@authServiceImpl.tieneAcceso('hateoas')")
	@GetMapping("/hateoas/{id}")
	public EntityModel<UsuarioDTO> listarHateoas(@PathVariable("id") Integer id) throws Exception{
		Usuario obj = service.listarPorId(id);
		if(obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}		
		UsuarioDTO dto = mapper.map(obj, UsuarioDTO.class);

		EntityModel<UsuarioDTO> recurso = EntityModel.of(dto);
		
		WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).listarPorId(id));
		WebMvcLinkBuilder link2 = linkTo(methodOn(this.getClass()).listarPorId(id));
		recurso.add(link1.withRel("estadovacu-info1"));
		recurso.add(link2.withRel("estadovacu-info2"));
		
		return recurso;
		
	}
	
}
