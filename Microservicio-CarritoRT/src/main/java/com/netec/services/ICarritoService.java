package com.netec.services;

import java.util.List;

import com.netec.entities.Articulo;

public interface ICarritoService {
	
	boolean insert(int id);
	boolean delete(int id);
	Articulo searchByName(String name);
	List<Articulo> showAll();
	double total();
}
