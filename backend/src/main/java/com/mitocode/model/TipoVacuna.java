package com.mitocode.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TipoVacuna {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idTipoVacuna;

	@Column(name = "descripcion", nullable = false, length = 50)
	private String descripcion;

	public Integer getIdTipoVacuna() {
		return idTipoVacuna;
	}

	public void setIdTipoVacuna(Integer idTipoVacuna) {
		this.idTipoVacuna = idTipoVacuna;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
