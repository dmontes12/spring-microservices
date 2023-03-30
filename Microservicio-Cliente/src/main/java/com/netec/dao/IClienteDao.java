package com.netec.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.netec.entities.Cliente;

public interface IClienteDao extends CrudRepository<Cliente, Integer> {
	Optional<Cliente> findByCorreo(String correo);
}
