package com.netec;

import java.util.Optional;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class MiFiltro implements GlobalFilter {

	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain){
		
		//Todo lo que se agrega antes del return es PRE-Filter
		exchange.getRequest()
			.mutate()
			.headers(x->x.add("token","1234"));
		
		
		return chain.filter(exchange).then(Mono.fromRunnable(()->{
			//Todo lo que se agregue en esta seccion sera POST-Filter
			exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
			//Enviando token del erequest al response
			Optional.ofNullable(exchange.getRequest().getHeaders().getFirst("token"))
				.ifPresent(t->exchange.getResponse().getHeaders().add("token", t));
		}));
	}
	
}
