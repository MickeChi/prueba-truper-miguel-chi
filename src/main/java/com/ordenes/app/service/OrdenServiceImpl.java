package com.ordenes.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.ordenes.app.dao.OrdenDAO;
import com.ordenes.app.dao.ProductoDAO;
import com.ordenes.app.dao.SucursalDAO;
import com.ordenes.app.dto.OrdenDTO;
import com.ordenes.app.dto.ProductoDTO;
import com.ordenes.app.dto.SucursalDTO;
import com.ordenes.app.entity.Orden;
import com.ordenes.app.entity.Producto;
import com.ordenes.app.entity.Sucursal;
import com.ordenes.app.utils.CustomMapper;

@Service
public class OrdenServiceImpl implements OrdenService{
	
	@Autowired
	private OrdenDAO ordenDAO;
	
	@Autowired
	private ProductoDAO productoDAO;
	
	@Autowired
	private SucursalDAO sucursalDAO;
	

	@Override
	@Transactional(readOnly = true)
	public List<OrdenDTO> findAll() {
		return ordenDAO.findAll().stream().map(o -> CustomMapper.mapOrdenToOrdenDTO(o)).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<OrdenDTO> findById(Long id) {
		Optional<Orden> ord = ordenDAO.findById(id);
	    Optional<OrdenDTO> ordResp = Optional.ofNullable(CustomMapper.mapOrdenToOrdenDTO(ord.get()));
		return ordResp;
	}

	@Override
	@Transactional
	public OrdenDTO save(OrdenDTO oDTO) {
		Orden ord = CustomMapper.mapOrdenDTOToOrden(oDTO, new Orden());
		
		if(oDTO.getSucursal() != null) {
			Optional<Sucursal> suc = sucursalDAO.findById(oDTO.getSucursal().getSucursal_id());
			ord.setSucursal(suc.get());
		}
		
		Orden ordenCreated = ordenDAO.save(ord);

		if(oDTO.getProductos().size() > 0) {
			List<Producto> addList = oDTO.getProductos().stream()
					.map(a -> this.addProductoToOrden(a, ordenCreated)).collect(Collectors.toList());
			ordenCreated.setProductos(addList);
		}
		
		
		return CustomMapper.mapOrdenToOrdenDTO(ordenCreated);
	}

	@Override
	@Transactional
	public OrdenDTO update(OrdenDTO ordDTO) {
		Optional<Orden> ordOp = ordenDAO.findById(ordDTO.getOrden_id());
		
		Orden ord = CustomMapper.mapOrdenDTOToOrden(ordDTO, ordOp.get());
		if(ordDTO.getSucursal() != null) {
			Optional<Sucursal> suc = sucursalDAO.findById(ordDTO.getSucursal().getSucursal_id());
			ord.setSucursal(suc.get());
		}
	
		Orden ordUpdated = ordenDAO.save(ord);
		
		if(ordDTO.getProductos().size() > 0) {
			List<Producto> addList = ordDTO.getProductos().stream()
					.map(a -> this.addProductoToOrden(a, ordUpdated)).collect(Collectors.toList());
			ordUpdated.setProductos(addList);
		}
		
		return CustomMapper.mapOrdenToOrdenDTO(ordUpdated);
	}
	
	
private Producto addProductoToOrden(ProductoDTO prodDTO, Orden ord) {
		
		Producto prodCreate = CustomMapper.mapProductDTOToProducto(prodDTO, new Producto());
		if(prodDTO.getProducto_id() != null) {
			Optional<Producto> prodOp = productoDAO.findById(prodDTO.getProducto_id());
			if(prodOp.isPresent()) {
				prodCreate = CustomMapper.mapProductDTOToProducto(prodDTO, prodOp.get());
			}
		}
		
		prodCreate.setOrden(ord);
		
		return productoDAO.save(prodCreate);
	}

	

	

}
