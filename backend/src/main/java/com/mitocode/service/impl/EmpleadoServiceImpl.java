package com.mitocode.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mitocode.model.Empleado;
import com.mitocode.repo.IEmpleadoRepo;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.service.IEmpleadoService;


@Service
public class EmpleadoServiceImpl extends CRUDImpl<Empleado, Integer> implements IEmpleadoService  {

	@Autowired
	private IEmpleadoRepo repo;

	@Override
	protected IGenericRepo<Empleado, Integer> getRepo() {
		return repo;
	}
	
	@Transactional
	@Override
	public Empleado registrarTransaccional(Empleado empleado) throws Exception {
		
		empleado.getDetalleVacunacion().forEach(det -> det.setEmpleado(empleado));
		
		repo.save(empleado);
		
		return empleado;
	}
	
	@Override
	public List<Empleado> buscarEstado(String descripcion) {
		return repo.buscarEstado(descripcion);
	}
	/*
	@Override
	public List<Empleado> buscarTipVacu(String nombre) {
		return repo.buscarTipVacu(nombre);
	}
	
	@Override
	public List<Empleado> buscarFecha(LocalDateTime fecha1, LocalDateTime fecha2) {
		return repo.buscarFecha(fecha1, fecha2.plusDays(1));
	}
	*/
}
