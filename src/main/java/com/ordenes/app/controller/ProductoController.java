package com.ordenes.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ordenes.app.dto.OrdenDTO;
import com.ordenes.app.dto.ProductoDTO;
import com.ordenes.app.service.OrdenService;
import com.ordenes.app.service.ProductoService;
import com.ordenes.app.service.SucursalService;
import com.ordenes.app.utils.CustomMapper;

@RestController
@RequestMapping("api/producto")
public class ProductoController {
	@Autowired
	private OrdenService ordenService;
	
	@Autowired
	private SucursalService sucursalService;
	
	@Autowired
	private ProductoService productoService;
	
	@GetMapping
	public ResponseEntity<?> findAll(){
		List<ProductoDTO> productoList = productoService.findAll();
		return ResponseEntity.ok(productoList);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id){
		Optional<ProductoDTO> producto = productoService.findById(id);
		if(!producto.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(producto);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody ProductoDTO prodDTO, @PathVariable("id") Long id){
		try {
			Optional<ProductoDTO> prodDTOResult = productoService.findById(id);
			if(!prodDTOResult.isPresent()) {
				return ResponseEntity.notFound().build();
			}		
			ProductoDTO prodUpdate = prodDTOResult.get();
			prodUpdate.setCodigo(prodDTO.getCodigo());
			prodUpdate.setDescripcion(prodDTO.getDescripcion());
			prodUpdate.setPrecio(prodDTO.getPrecio());
			prodUpdate.setOrden_id(prodDTO.getOrden_id());

			return ResponseEntity.status(HttpStatus.CREATED).body(productoService.update(prodUpdate));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
		
	}
	
	
	
	
}
