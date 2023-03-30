package com.netec.feigns;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.netec.entities.Articulo;

@FeignClient(name="micro-articulo")
public interface IArticuloFeign {
	
	@GetMapping("/articulo/{id}")
	Articulo findById(@PathVariable("id") int id);

}
