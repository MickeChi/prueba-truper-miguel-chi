package com.ordenes.app.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class OrdenDTO {
	
	private Long orden_id;
	
	private Date fecha;
	
	private Float total;
	
	private SucursalDTO sucursal;
	
	private List<ProductoDTO> productos;

}
