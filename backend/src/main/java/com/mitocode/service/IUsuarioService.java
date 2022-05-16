package com.mitocode.service;

import com.mitocode.model.Usuario;

public interface IUsuarioService extends ICRUD<Usuario, Integer> {

	Usuario registrarTransaccional(Usuario usuario/*, List<Examen> examenes*/) throws Exception;
	
}
