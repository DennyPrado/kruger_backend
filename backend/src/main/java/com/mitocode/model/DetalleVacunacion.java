package com.mitocode.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class DetalleVacunacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idDetalleVacunacion;
	
	@ManyToOne
	@JoinColumn(name = "id_empleado", nullable = false, foreignKey = @ForeignKey(name = "FK_empleado_detallevacunacion"))
	private Empleado empleado;
	
	@ManyToOne
	@JoinColumn(name = "id_tipo_vacuna", nullable = false, foreignKey = @ForeignKey(name = "FK_empleado_tipovacuna"))
	private TipoVacuna tipoVacuna;
	
	@Column(nullable = false)
	private LocalDateTime fechaVacunacion;
	
	@Column(nullable = false)
	private Integer numDosis;

	public Integer getIdDetalleVacunacion() {
		return idDetalleVacunacion;
	}

	public void setIdDetalleVacunacion(Integer idDetalleVacunacion) {
		this.idDetalleVacunacion = idDetalleVacunacion;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public TipoVacuna getTipoVacuna() {
		return tipoVacuna;
	}

	public void setTipoVacuna(TipoVacuna tipoVacuna) {
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
