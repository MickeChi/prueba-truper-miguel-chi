package com.ordenes.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ordenes.app.dto.ProductoDTO;
import com.ordenes.app.dto.SucursalDTO;

@Service
public class SucursalServiceImpl implements SucursalService{

	@Override
	@Transactional(readOnly = true)
	public List<SucursalDTO> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<SucursalDTO> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public SucursalDTO save(SucursalDTO entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public SucursalDTO update(SucursalDTO entity) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
