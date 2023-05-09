package com.ordenes.app.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity(name = "sucursales")
public class Sucursal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long sucursal_id;
	
	@Column(name = "nombre", length = 50, nullable = false)
	private String nombre;
	
	@OneToMany(mappedBy = "sucursal", fetch = FetchType.EAGER)
	private List<Orden> ordenes;

}
