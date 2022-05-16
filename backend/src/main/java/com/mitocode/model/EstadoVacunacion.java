package com.mitocode.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class EstadoVacunacion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idEstadoVacunacion;

	@Column(name = "descripcion", nullable = false, length = 50)
	private String descripcion;

	public Integer getIdEstadoVacunacion() {
		return idEstadoVacunacion;
	}

	public void setIdEstadoVacunacion(Integer idEstadoVacunacion) {
		this.idEstadoVacunacion = idEstadoVacunacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
