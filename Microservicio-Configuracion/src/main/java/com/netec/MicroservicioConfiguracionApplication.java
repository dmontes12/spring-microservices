package com.netec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class MicroservicioConfiguracionApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioConfiguracionApplication.class, args);
	}

}
