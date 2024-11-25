package com.mueblesstgo.ms_calculo_planilla_sueldos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsCalculoPlanillaSueldosApplication {
	public static void main(String[] args) {
		SpringApplication.run(MsCalculoPlanillaSueldosApplication.class, args);
	}
}
