package com.mitocode.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl {
	
	public boolean tieneAcceso(String path) {
		boolean rpta = false;

		String metodoRol = "";

		switch (path) {
		case "listar":
			metodoRol = "ADMIN";
			break;
			
		case "listarporid":
			metodoRol = "ADMIN";
			break;
			
		case "registrar":
			metodoRol = "ADMIN";
			break;

		case "modificar":
			metodoRol = "ADMIN,USER";
			break;
			
		case "modificaradmin":
			metodoRol = "ADMIN";
			break;
			
		case "eliminar":
			metodoRol = "ADMIN";
			break;
			
		case "hateoas":
			metodoRol = "ADMIN";
			break;
		}
		
		String metodoRoles[] = metodoRol.split(",");
		
		//Informacion del usuario logueado
		Authentication usuarioLogueado = SecurityContextHolder.getContext().getAuthentication();
		
		for (GrantedAuthority auth : usuarioLogueado.getAuthorities()) {
			String rolUser = auth.getAuthority();
			System.out.println(rolUser);

			for (String rolMet : metodoRoles) {
				if (rolUser.equalsIgnoreCase(rolMet)) {
					rpta = true;
				}
			}
		}
		
		return rpta;
		
	}

}
