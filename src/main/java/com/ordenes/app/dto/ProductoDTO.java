package com.ordenes.app.dto;

import lombok.Data;

@Data
public class ProductoDTO {
	
	private Long producto_id;
	
	private String codigo;
	
	private String descripcion;
	
	private Float precio;
	
	private Long orden_id;
}
