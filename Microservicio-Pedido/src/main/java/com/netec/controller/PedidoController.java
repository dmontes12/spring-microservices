package com.netec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netec.entities.Pedido;
import com.netec.services.IPedidoService;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired
	private IPedidoService pedidoService;

	@GetMapping
	public List<Pedido> showAll() {
		return pedidoService.showAll();
	}

	@PostMapping("/cliente/{correo}")
	public boolean insert(@PathVariable String correo) {
		return pedidoService.insert(correo);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable int id) {

		pedidoService.delete(id);
	}

}
