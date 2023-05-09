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
@RequestMapping("api/orden")
public class OrdenController {
	
	@Autowired
	private OrdenService ordenService;
	
	@Autowired
	private SucursalService sucursalService;
	
	@Autowired
	private ProductoService productoService;
	
	@GetMapping
	public ResponseEntity<?> findAll(){
		List<OrdenDTO> ordenList = ordenService.findAll();
		return ResponseEntity.ok(ordenList);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id){
		Optional<OrdenDTO> orden = ordenService.findById(id);
		if(!orden.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(orden);
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody OrdenDTO ordDTO){
		OrdenDTO orden = ordenService.save(ordDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(orden);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody OrdenDTO ordDTO, @PathVariable("id") Long id){
		try {
			Optional<OrdenDTO> resultOrdDTO = ordenService.findById(id);
			if(!resultOrdDTO.isPresent()) {
				return ResponseEntity.notFound().build();
			}		
			OrdenDTO ordUpdate = CustomMapper.mapOrdenDTOToOrdenDTO(ordDTO, resultOrdDTO.get());	
						
			return ResponseEntity.status(HttpStatus.CREATED).body(ordenService.update(ordUpdate));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
		
	}

}
