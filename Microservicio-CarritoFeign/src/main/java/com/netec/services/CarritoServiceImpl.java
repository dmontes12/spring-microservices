package com.netec.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;

import com.netec.entities.Articulo;
import com.netec.entities.Carrito;
import com.netec.feigns.IArticuloFeign;



@Service
public class CarritoServiceImpl implements ICarritoService {
	
	@Autowired
	private IArticuloFeign feign;
	
	@Autowired
	private Carrito carrito;
	
	@Autowired
	private CircuitBreakerFactory cb;
	
	private final Logger log = LoggerFactory.getLogger(CarritoServiceImpl.class);

	@Override
	public boolean insert(int id) {
		// TODO Auto-generated method stub
		
		return cb.create("circuito1")
				.run(()->{
					try {
						Articulo art = feign.findById(id);
						this.carrito.getArticulos().add(art);
						log.info("Circuito cerrado");
						return true;
					}catch(Exception ex){
						log.error("Error: "+ex);
					}
					return false;	
				}, t->caminoSeguro(id));
	}
	
	private boolean caminoSeguro(int id){
		log.info("Ejecutando camino seguro");
		return false;
	}

	@Override
	public List<Articulo> showAll() {
		// TODO Auto-generated method stub
		return this.carrito.getArticulos();
	}

	@Override
	public double total() {
		// TODO Auto-generated method stub
		return this.carrito.getArticulos()
				.stream()
				.mapToDouble(t->t.getPrecio())
				.sum();
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		List<Articulo> emptyList = new ArrayList<>();
		carrito.setArticulos(emptyList);
		
	}


}
