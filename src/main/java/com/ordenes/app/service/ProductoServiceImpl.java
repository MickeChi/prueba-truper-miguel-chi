package com.ordenes.app.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.ordenes.app.dao.OrdenDAO;
import com.ordenes.app.dao.ProductoDAO;
import com.ordenes.app.dto.ProductoDTO;
import com.ordenes.app.entity.Producto;
import com.ordenes.app.utils.CustomMapper;

@Service
public class ProductoServiceImpl implements ProductoService{
	
	@Autowired
	private OrdenDAO ordenDAO;
	
	@Autowired
	private ProductoDAO productoDAO;

	@Override
	@Transactional(readOnly = true)
	public List<ProductoDTO> findAll() {
		return productoDAO.findAll().stream().map(o -> CustomMapper.mapProductToProductoDTO(o)).collect(Collectors.toList());

	}

	@Override
	@Transactional(readOnly = true)
	public Optional<ProductoDTO> findById(Long id) {
		Optional<Producto> prod = productoDAO.findById(id);
	    Optional<ProductoDTO> prodDTO = Optional.ofNullable(CustomMapper.mapProductToProductoDTO(prod.get()));
		return prodDTO;
	}

	@Override
	@Transactional
	public ProductoDTO save(ProductoDTO entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public ProductoDTO update(ProductoDTO prodDTO) {
		Optional<Producto> prodOp = productoDAO.findById(prodDTO.getProducto_id());
		Producto prod = CustomMapper.mapProductDTOToProducto(prodDTO, prodOp.get());
		
		return CustomMapper.mapProductToProductoDTO(productoDAO.save(prod));
	}

}
