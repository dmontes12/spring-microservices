package com.netec.services;

import java.util.List;

import com.netec.entities.Pedido;

public interface IPedidoService {
	Pedido findById(int id);
	boolean insert(String correo);
	List<Pedido> showAll();
	void delete(int id);
}
