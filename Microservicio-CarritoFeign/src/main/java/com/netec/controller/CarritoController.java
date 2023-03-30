package com.netec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netec.entities.Articulo;
import com.netec.services.ICarritoService;

@RestController
@RequestMapping("/carrito")
public class CarritoController {
	
	@Autowired
	private ICarritoService servicio;
	
	@PostMapping("/{id}")
	public boolean insert(@PathVariable("id")int id) {
		return servicio.insert(id);
	}
	
	@GetMapping
	public List<Articulo> findAll(){
		return servicio.showAll();
	}
	
	@GetMapping("/total")
	public double total() {
		return servicio.total();
	}
	
	@DeleteMapping()
	public void delete() {
		
		servicio.delete();
	}

}
