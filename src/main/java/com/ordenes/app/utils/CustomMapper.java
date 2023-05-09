package com.ordenes.app.utils;

import java.util.List;
import java.util.stream.Collectors;

import com.ordenes.app.dto.OrdenDTO;
import com.ordenes.app.dto.ProductoDTO;
import com.ordenes.app.dto.SucursalDTO;
import com.ordenes.app.entity.Orden;
import com.ordenes.app.entity.Producto;
import com.ordenes.app.entity.Sucursal;

public class CustomMapper {
	
	public static OrdenDTO mapOrdenDTOToOrdenDTO(OrdenDTO ori, OrdenDTO dest) {
		if(dest.getOrden_id() == null) {
			dest.setOrden_id(ori.getOrden_id());
		}
		
		if(ori.getSucursal() != null) {
			SucursalDTO sucDTO = new SucursalDTO();
			sucDTO.setNombre(ori.getSucursal().getNombre());
			sucDTO.setSucursal_id(ori.getSucursal().getSucursal_id());
			dest.setSucursal(sucDTO);
		}
		
		dest.setProductos(ori.getProductos());
		dest.setTotal(ori.getTotal());
		dest.setFecha(ori.getFecha());
		
		return dest;
		
	}
	
	
	public static OrdenDTO mapOrdenToOrdenDTO(Orden o) {
		OrdenDTO ordDTO = new OrdenDTO();
		ordDTO.setOrden_id(o.getOrden_id());
		ordDTO.setFecha(o.getFecha());
		ordDTO.setTotal(o.getTotal());
		if(o.getSucursal() != null) {
			SucursalDTO sucDTO = new SucursalDTO();
			sucDTO.setNombre(o.getSucursal().getNombre());
			sucDTO.setSucursal_id(o.getSucursal().getSucursal_id());
			ordDTO.setSucursal(sucDTO);
		}
		
		if(o.getProductos() != null && o.getProductos().size() > 0) {
			List<ProductoDTO> listprod = o.getProductos().stream().map(p -> mapProductToProductoDTO(p)).collect(Collectors.toList());
			ordDTO.setProductos(listprod);
		}
		return ordDTO;	
	}
	
	public static Orden mapOrdenDTOToOrden(OrdenDTO ori, Orden dest) {
		if(dest.getOrden_id() == null) {
			dest.setOrden_id(ori.getOrden_id());
		}
		
		dest.setFecha(ori.getFecha());
		dest.setTotal(ori.getTotal());
		
		return dest;
	}
	
	public static SucursalDTO mapSucursalToSucursalDTO(Sucursal sucursal) {
		return null;
		
	}
	

	public static Producto mapProductDTOToProducto(ProductoDTO ori, Producto dest) {
		if(dest.getProducto_id() != null) {
			dest.setProducto_id(ori.getProducto_id());
		}
		
		dest.setCodigo(ori.getCodigo());
		dest.setDescripcion(ori.getDescripcion());
		dest.setPrecio(ori.getPrecio());
		
		return dest;
	}
	
	
	public static ProductoDTO mapProductToProductoDTO(Producto prod) {
		ProductoDTO prodDTO = new ProductoDTO();
		if(prod.getProducto_id() != null) {
			prodDTO.setProducto_id(prod.getProducto_id());
		}
		
		prodDTO.setOrden_id(prod.getOrden().getOrden_id());
		prodDTO.setCodigo(prod.getCodigo());
		prodDTO.setDescripcion(prod.getDescripcion());
		prodDTO.setPrecio(prod.getPrecio());
		
		return prodDTO;
	}
	
	
	

}
