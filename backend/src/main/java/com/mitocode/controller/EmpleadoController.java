package com.mitocode.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mitocode.dto.EmpleadoDTO;
import com.mitocode.dto.FiltroConsultaDTO;
import com.mitocode.dto.FiltroDosConsultaDTO;
import com.mitocode.exception.ModeloNotFoundException;
import com.mitocode.model.Empleado;
import com.mitocode.service.IEmpleadoService;

@RestController
@RequestMapping("/empleado")
public class EmpleadoController {

	@Autowired
	private IEmpleadoService service;
	
	@Autowired
	private ModelMapper mapper;
	
	@PreAuthorize("@authServiceImpl.tieneAcceso('listar')")
	@GetMapping
	public ResponseEntity<List<EmpleadoDTO>> listar() throws Exception{		
		List<EmpleadoDTO> lista = service.listar().stream().map(p -> mapper.map(p, EmpleadoDTO.class)).collect(Collectors.toList());
		return new ResponseEntity<>(lista, HttpStatus.OK);
	}
	
	@PreAuthorize("@authServiceImpl.tieneAcceso('listarporid')")
	@GetMapping("/{id}")
	public ResponseEntity<EmpleadoDTO> listarPorId(@PathVariable("id") Integer id) throws Exception{
		Empleado obj = service.listarPorId(id);
		
		if(obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}
		
		EmpleadoDTO dto = mapper.map(obj, EmpleadoDTO.class);
		
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@PreAuthorize("@authServiceImpl.tieneAcceso('registrar')")
	@PostMapping
	public ResponseEntity<Void> registrar(@Valid @RequestBody EmpleadoDTO dtoRequest) throws Exception{
		Empleado emp = mapper.map(dtoRequest, Empleado.class);
		Empleado obj = service.registrarTransaccional(emp);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdEmpleado()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PreAuthorize("@authServiceImpl.tieneAcceso('modificar')")
	@PutMapping
	public ResponseEntity<Empleado> modificar(@Valid @RequestBody EmpleadoDTO dtoRequest) throws Exception{
		Empleado obj = service.listarPorId(dtoRequest.getIdEmpleado());
		
		if(obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + dtoRequest.getIdEmpleado());
		}
		
		Empleado emp = mapper.map(dtoRequest, Empleado.class);
		Empleado empleado = service.modificar(emp);		
		
		return new ResponseEntity<>(empleado, HttpStatus.OK);
	}
	
	@PreAuthorize("@authServiceImpl.tieneAcceso('eliminar')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception{
		Empleado obj = service.listarPorId(id);
		
		if(obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}
		
		service.eliminar(id);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PreAuthorize("@authServiceImpl.tieneAcceso('hateoas')")
	@GetMapping("/hateoas/{id}")
	public EntityModel<EmpleadoDTO> listarHateoas(@PathVariable("id") Integer id) throws Exception{
		Empleado obj = service.listarPorId(id);
		if(obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}		
		EmpleadoDTO dto = mapper.map(obj, EmpleadoDTO.class);

		EntityModel<EmpleadoDTO> recurso = EntityModel.of(dto);
		
		WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).listarPorId(id));
		WebMvcLinkBuilder link2 = linkTo(methodOn(this.getClass()).listarPorId(id));
		recurso.add(link1.withRel("empleado-info1"));
		recurso.add(link2.withRel("empleado-info2"));
		
		return recurso;
		
	}
	
	//---------------- FILTROS

	@PostMapping("/buscar/estado")
	public ResponseEntity<List<EmpleadoDTO>> buscarEstado(@RequestBody FiltroConsultaDTO filtro) throws Exception {
		List<Empleado> empleado = new ArrayList<>();

		empleado = service.buscarEstado(filtro.getDescripcion());
		
		List<EmpleadoDTO> empleadoDTO = mapper.map(empleado, new TypeToken<List<EmpleadoDTO>>() {}.getType());

		return new ResponseEntity<List<EmpleadoDTO>>(empleadoDTO, HttpStatus.OK);
	}
	/*
	@PostMapping("/buscar/tipo")
	public ResponseEntity<List<EmpleadoDTO>> buscarTipo(@RequestBody FiltroDosConsultaDTO filtro) throws Exception {
		List<Empleado> empleado = new ArrayList<>();

		empleado = service.buscarTipVacu(filtro.getNombre());
		
		List<EmpleadoDTO> empleadoDTO = mapper.map(empleado, new TypeToken<List<EmpleadoDTO>>() {}.getType());

		return new ResponseEntity<List<EmpleadoDTO>>(empleadoDTO, HttpStatus.OK);
	}
	
	@GetMapping("/buscar/fecha")
	public ResponseEntity<List<EmpleadoDTO>> buscarFecha(@RequestParam(value = "fecha1") String fecha1, @RequestParam(value = "fecha2") String fecha2) throws Exception {
		List<Empleado> empleado = new ArrayList<>();

		empleado = service.buscarFecha(LocalDateTime.parse(fecha1), LocalDateTime.parse(fecha2));
		List<EmpleadoDTO> EmpleadoDTO = mapper.map(empleado, new TypeToken<List<EmpleadoDTO>>() {}.getType());

		return new ResponseEntity<>(EmpleadoDTO, HttpStatus.OK);
	}
	*/
	//------------------------------------------------------------
	
}
