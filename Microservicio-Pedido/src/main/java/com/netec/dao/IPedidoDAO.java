package com.netec.dao;

import org.springframework.data.repository.CrudRepository;

import com.netec.entities.Pedido;

public interface IPedidoDAO extends CrudRepository<Pedido, Integer> {
	
}
