package com.ordenes.app.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity(name = "ordenes")
public class Orden {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orden_id;
	
	@Column(name = "fecha", nullable = false)
	private Date fecha;
	
	@Column(name = "total", nullable = false)
	private Float total;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sucursal_id", referencedColumnName = "sucursal_id", nullable = false)
	private Sucursal sucursal;
	
	@OneToMany(mappedBy = "orden", fetch = FetchType.EAGER)
	private List<Producto> productos;

}
