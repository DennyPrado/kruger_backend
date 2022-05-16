package com.mitocode.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.model.EstadoVacunacion;
import com.mitocode.repo.IEstadoVacunacionRepo;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.service.IEstadoVacunacionService;

@Service
public class EstadoVacunacionServiceImpl extends CRUDImpl<EstadoVacunacion, Integer> implements IEstadoVacunacionService  {

	@Autowired
	private IEstadoVacunacionRepo repo;

	@Override
	protected IGenericRepo<EstadoVacunacion, Integer> getRepo() {
		return repo;
	}
	
}
