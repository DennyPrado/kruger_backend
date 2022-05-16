package com.mitocode.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mitocode.model.Usuario;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.repo.IUsuarioRepo;
import com.mitocode.service.IUsuarioService;

@Service
public class UsuariosServiceImpl extends CRUDImpl<Usuario, Integer> implements IUsuarioService   {

	@Autowired
	private IUsuarioRepo repo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	protected IGenericRepo<Usuario, Integer> getRepo() {
		return repo;
	}
	
	@Transactional
	@Override
	public Usuario registrarTransaccional(Usuario usuario/*, List<Examen> examenes*/) throws Exception {
		
		String pass = usuario.getPassword();
		String passEncrip = passwordEncoder.encode(pass);
		
		usuario.setPassword(passEncrip);
		
		repo.save(usuario);
		
		return usuario;
	}
	
}
