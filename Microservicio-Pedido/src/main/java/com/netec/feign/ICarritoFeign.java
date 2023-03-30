package com.netec.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(name="micro-carritof")
public interface ICarritoFeign {
	@GetMapping("/carrito/total")
	Double getCarritoTotal();
	@DeleteMapping("/carrito")
	void delete();
}
