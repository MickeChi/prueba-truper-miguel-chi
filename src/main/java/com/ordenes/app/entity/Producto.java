package com.ordenes.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity(name = "productos")
public class Producto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long producto_id;
	
	@Column(name = "codigo", length = 20, nullable = false)
	private String codigo;
	
	@Column(name = "descripcion", length = 200, nullable = false)
	private String descripcion;
	
	@Column(name = "precio", nullable = false)
	private Float precio;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "orden_id", referencedColumnName = "orden_id", nullable = false)
	private Orden orden;
}
