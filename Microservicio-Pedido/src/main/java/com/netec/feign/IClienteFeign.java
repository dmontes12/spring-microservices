package com.netec.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.netec.entities.Cliente;

@FeignClient(name="micro-cliente")
public interface IClienteFeign {
	@GetMapping("/cliente/correo/{correo}")
	Cliente findByCorreo(@PathVariable String correo);
}
