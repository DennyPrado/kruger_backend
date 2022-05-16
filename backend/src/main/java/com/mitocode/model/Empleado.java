package com.mitocode.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Empleado {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idEmpleado;
	
	@Column(nullable = false, length = 10)
	private String cedula;
	
	@Column(nullable = false)
	private String nombres;
	
	@Column(nullable = false)
	private String apellidos;
	
	@Column(nullable = false)
	private String correoElectronico;
	
	@Column(nullable = true)
	private LocalDateTime fechaNacimiento;
	
	@Column(nullable = true)
	private String direccionDomicilio;
	
	@Column(nullable = true)
	private String telefonoMovil;
	
	@ManyToOne
	@JoinColumn(name = "id_estado_vacunacion", nullable = true, foreignKey = @ForeignKey(name = "FK_empleado_estadovacunacion"))
	private EstadoVacunacion estadoVacunacion;
	
	@OneToMany(mappedBy = "empleado", cascade = { CascadeType.ALL }, orphanRemoval = true)
	private List<DetalleVacunacion> detalleVacunacion;

	public Integer getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Integer idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public LocalDateTime getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDateTime fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getDireccionDomicilio() {
		return direccionDomicilio;
	}

	public void setDireccionDomicilio(String direccionDomicilio) {
		this.direccionDomicilio = direccionDomicilio;
	}

	public String getTelefonoMovil() {
		return telefonoMovil;
	}

	public void setTelefonoMovil(String telefonoMovil) {
		this.telefonoMovil = telefonoMovil;
	}

	public EstadoVacunacion getEstadoVacunacion() {
		return estadoVacunacion;
	}

	public void setEstadoVacunacion(EstadoVacunacion estadoVacunacion) {
		this.estadoVacunacion = estadoVacunacion;
	}

	public List<DetalleVacunacion> getDetalleVacunacion() {
		return detalleVacunacion;
	}

	public void setDetalleVacunacion(List<DetalleVacunacion> detalleVacunacion) {
		this.detalleVacunacion = detalleVacunacion;
	}
}
