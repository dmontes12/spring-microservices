package com.netec.services;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.netec.dao.IArticuloDAO;
import com.netec.entities.Articulo;

@Service
public class ArticuloServiceImpl implements IArticuloService{
	
	@Autowired
	private IArticuloDAO dao;
	@Value("${server.port}")
	private int port;

	@Override
	public Articulo insert(Articulo art) {
		// TODO Auto-generated method stub
		return dao.save(art);
	}

	@Override
	public Articulo findById(int id) {
		// TODO Auto-generated method stub
		return dao.findById(id)
				.stream()
				.filter(t->t!=null)
				.peek(t->t.setPort(port))
				.findFirst()
				.orElseThrow(()-> new RuntimeException("No existe: "+id));
	}

	@Override
	public void update(Articulo art) {
		// TODO Auto-generated method stub
		Stream.of(art).filter(t->dao.existsById(t.getId()))
					.peek(t->dao.save(t))
					.findFirst()
					.orElseThrow(()-> new RuntimeException("No existe: "+art.getId()));
		
	}

	@Override
	public void deleteById(int id) {
		dao.deleteById(id);
		
	}

	@Override
	public List<Articulo> findAll() {
		// TODO Auto-generated method stub
		return (List<Articulo>) dao.findAll();
	}

	@Override
	public List<Articulo> findByMarca(String marca) {
		// TODO Auto-generated method stub
		return dao.buscarPorMarca(marca)
				.orElseThrow(()-> new RuntimeException("No existe: "+marca));
	}

}
