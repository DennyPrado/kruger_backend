package com.mitocode.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mitocode.model.Empleado;

public interface IEmpleadoRepo extends IGenericRepo<Empleado, Integer> {

	@Query("FROM Empleado e WHERE e.estadoVacunacion.descripcion = :descripcion")
	List<Empleado> buscarEstado(@Param("descripcion") String descripcion);
/*	
	@Query("FROM Empleado e WHERE e.detalleVacunacion.tipoVacuna.descripcion = :nombre")
	List<Empleado> buscarTipVacu(@Param("nombre") String nombre);
	
	@Query("FROM Empleado e WHERE e.detalleVacunacion.fechaVacunacions BETWEEN :fechaConsulta1 AND :fechaConsulta2")
	List<Empleado> buscarFecha(@Param("fechaConsulta1") LocalDateTime fechaConsulta1,  @Param("fechaConsulta2") LocalDateTime fechaConsulta2);
*/
}
