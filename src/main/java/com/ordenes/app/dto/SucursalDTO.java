package com.ordenes.app.dto;

import java.util.List;

import lombok.Data;

@Data
public class SucursalDTO {
	
	private Long sucursal_id;
	
	
	private String nombre;
	
	
	private List<OrdenDTO> ordenes;

}
