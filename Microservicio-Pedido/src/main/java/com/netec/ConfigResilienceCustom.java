package com.netec;

import java.time.Duration;

import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;

@Configuration
public class ConfigResilienceCustom {
	
	@Bean
	public Customizer<Resilience4JCircuitBreakerFactory> config(){
		
		return factory -> factory.configureDefault(id -> {
			if(id.equals("circuito1")) {
				//conf circuito 1
				return new Resilience4JConfigBuilder(id)
						.circuitBreakerConfig(CircuitBreakerConfig.custom()
								.slidingWindowSize(10)//llamadas en el estado cerrado
								.failureRateThreshold(50)//tasa de fallas en porcentaje
								.waitDurationInOpenState(Duration.ofSeconds(10L))//DURACION DE OPENSTATE
								.permittedNumberOfCallsInHalfOpenState(5)//llamadas en semi abierto
								.build()
								)
						.build();
			}else if(id.equals("circuito2")) {
				//conf si hubiera circuito2
				return null;
			}else {
				//configuracion de todos los demas circuitos
				return new Resilience4JConfigBuilder(id)
						.circuitBreakerConfig(CircuitBreakerConfig.custom()
								.slidingWindowSize(20)//llamadas en el estado cerrado
								.failureRateThreshold(50)//tasa de fallas en porcentaje
								.waitDurationInOpenState(Duration.ofSeconds(20L))//DURACION DE OPENSTATE
								.permittedNumberOfCallsInHalfOpenState(10)//llamadas en semi abierto
								.build()
								)
						.build();
			}
		});
	}

}
