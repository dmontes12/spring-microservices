package com.netec.services;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netec.dao.IClienteDao;
import com.netec.entities.Cliente;

@Service
public class IClienteServiceImpl implements IClienteService{
	
	@Autowired
	private IClienteDao clienteDao;

	@Override
	public Cliente insert(Cliente cliente) {
		// TODO Auto-generated method stub
		return clienteDao.save(cliente);
	}

	@Override
	public Cliente findById(int id) {
		// TODO Auto-generated method stub
		return clienteDao.findById(id)
				.stream()
				.filter(t->t!=null)
				.findFirst()
				.orElseThrow(()-> new RuntimeException("No existe: "+id));
	}


	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		clienteDao.deleteById(id);
		
	}

	@Override
	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		return (List<Cliente>) clienteDao.findAll();
	}

	@Override
	public Cliente findByCorreo(String correo) {
		// TODO Auto-generated method stub
		return clienteDao.findByCorreo(correo)
				/*.stream()
				.filter(t -> t!=null)
				.findFirst()*/
				.orElseThrow(() -> new RuntimeException("No existe: "+correo));
	}

	@Override
	public void update(Cliente cliente) {
		// TODO Auto-generated method stub
		Stream.of(cliente).filter(t->clienteDao.existsById(t.getId()))
		.peek(t->clienteDao.save(t))
		.findFirst()
		.orElseThrow(()-> new RuntimeException("No existe: "+cliente.getId()));
	}



}
