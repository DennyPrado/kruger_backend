package com.mitocode.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

public class DetalleVacunacionDTO {

	private Integer idDetalleVacunacion;
	
	//@JsonIgnore
	private EmpleadoDTO empleado;
	
	@NotNull
	private TipoVacunaDTO tipoVacuna;
	
	@NotNull
	private LocalDateTime fechaVacunacion;
	
	@NotNull
	private Integer numDosis;

	public Integer getIdDetalleVacunacion() {
		return idDetalleVacunacion;
	}

	public void setIdDetalleVacunacion(Integer idDetalleVacunacion) {
		this.idDetalleVacunacion = idDetalleVacunacion;
	}

	public EmpleadoDTO getEmpleadoDTO() {
		return empleado;
	}

	public void setEmpleadoDTO(EmpleadoDTO empleado) {
		this.empleado = empleado;
	}

	public TipoVacunaDTO getTipoVacuna() {
		return tipoVacuna;
	}

	public void setTipoVacuna(TipoVacunaDTO tipoVacuna) {
		this.tipoVacuna = tipoVacuna;
	}

	public LocalDateTime getFechaVacunacion() {
		return fechaVacunacion;
	}

	public void setFechaVacunacion(LocalDateTime fechaVacunacion) {
		this.fechaVacunacion = fechaVacunacion;
	}

	public Integer getNumDosis() {
		return numDosis;
	}

	public void setNumDosis(Integer numDosis) {
		this.numDosis = numDosis;
	}
}
