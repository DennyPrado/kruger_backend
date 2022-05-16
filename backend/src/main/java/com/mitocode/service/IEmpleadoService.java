package com.mitocode.service;

import java.time.LocalDateTime;
import java.util.List;

import com.mitocode.model.Empleado;

public interface IEmpleadoService extends ICRUD<Empleado, Integer> {

	Empleado registrarTransaccional(Empleado empleado) throws Exception;
	
	List<Empleado> buscarEstado(String descripcion) throws Exception;
/*	
	List<Empleado> buscarTipVacu(String nombre) throws Exception;

	List<Empleado> buscarFecha(LocalDateTime fecha1, LocalDateTime fecha2) throws Exception;
*/
}
