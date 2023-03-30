package com.netec.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import com.netec.dao.IPedidoDAO;
import com.netec.entities.Cliente;
import com.netec.entities.Pedido;
import com.netec.feign.ICarritoFeign;
import com.netec.feign.IClienteFeign;

@Service
public class IPedidoServiceImpl implements IPedidoService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private IClienteFeign feignCliente;
	
	@Autowired
	private ICarritoFeign feignCarrito;

	@Autowired
	private IPedidoDAO pedidoDao;
	
	@Autowired
	private CircuitBreakerFactory cb;

	@Override
	public boolean insert(String correo) {
		// TODO Auto-generated method stub
		return cb.create("circuito1").run(() -> {
			try {
				Cliente cliente = feignCliente.findByCorreo(correo);
				Double total = feignCarrito.getCarritoTotal();
				Pedido pedido = new Pedido();
				pedido.setCorreoCliente(cliente.getCorreo());
				pedido.setNombreCliente(cliente.getNombre());
				pedido.setTotal(total);
				if(pedidoDao.save(pedido) != null)feignCarrito.delete();
				log.info("Circuito cerrado");
				return true;
			} catch (Exception ex) {
				log.error("Errorson: " + ex);
			}
			return false;
		}, t -> caminoSeguro(correo));
	}

	private boolean caminoSeguro(String correo) {
		log.info("Ejecutando camino seguron!!");
		return false;
	}

	@Override
	public List<Pedido> showAll() {
		// TODO Auto-generated method stub
		return (List<Pedido>) pedidoDao.findAll();
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		pedidoDao.deleteById(id);
		
	}

	@Override
	public Pedido findById(int id) {
		// TODO Auto-generated method stub
		return pedidoDao.findById(id).get();
	}

}