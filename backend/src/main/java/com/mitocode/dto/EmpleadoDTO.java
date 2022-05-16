package com.mitocode.dto;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Value;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Empleado DTO Data")
public class EmpleadoDTO {
	
	private Integer idEmpleado;
	
	@Schema(description = "Cedula del Empleado")
	@NotEmpty(message="El nombre del producto es obligatorio")
	@Size(min = 10, message = "{cedula.size}")
	@Pattern(regexp = "[0-9]+", message="La Cedula Solo Puede contener Valores Numericos") 
	private String cedula;
	
	@Schema(description = "Nombres del Empleado")
	@NotNull
	@Pattern(regexp = "[A-Z a-z]+", message="Los Nombres solo pueden contener Letras")
	private String nombres;
	
	@Schema(description = "Apellidos del Empleado")
	@NotNull
	@Pattern(regexp = "[A-Z a-z]+", message="Los Apellidos solo pueden contener Letras")
	private String apellidos;
	
	@Schema(description = "Correo Electronico del Empleado")
	@NotEmpty
	@Email
	private String correoElectronico;
	
	@Schema(description = "Fecha de Nacimiento del Empleado")
	private LocalDateTime fechaNacimiento;
	
	@Schema(description = "Direccion del Empleado")
	private String direccionDomicilio;
	
	@Schema(description = "Telefono del Empleado")
	private String telefonoMovil;
	
	@Schema(description = "Estado de Vacunacion del Empleado")
	private EstadoVacunacionDTO estadoVacunacion;
	
	@Schema(description = "Detalle de Vacunacion del Empleado")
	private List<DetalleVacunacionDTO> detalleVacunacion;
	
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

	public EstadoVacunacionDTO getEstadoVacunacion() {
		return estadoVacunacion;
	}

	public void setEstadoVacunacion(EstadoVacunacionDTO estadoVacunacion) {
		this.estadoVacunacion = estadoVacunacion;
	}
	
	public List<DetalleVacunacionDTO> getDetalleVacunacion() {
		return detalleVacunacion;
	}

	public void setDetalleVacunacion(List<DetalleVacunacionDTO> detalleVacunacion) {
		this.detalleVacunacion = detalleVacunacion;
	}
	
}
