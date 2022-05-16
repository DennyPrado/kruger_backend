package com.mitocode.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.model.TipoVacuna;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.repo.ITipoVacunaRepo;
import com.mitocode.service.ITipoVacunaService;



@Service
public class TipoVacunaServiceImpl extends CRUDImpl<TipoVacuna, Integer> implements ITipoVacunaService  {

	@Autowired
	private ITipoVacunaRepo repo;

	@Override
	protected IGenericRepo<TipoVacuna, Integer> getRepo() {
		return repo;
	}
	
}
