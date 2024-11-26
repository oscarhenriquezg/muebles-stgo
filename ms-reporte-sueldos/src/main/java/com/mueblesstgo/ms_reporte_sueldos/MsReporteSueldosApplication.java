package com.mueblesstgo.ms_reporte_sueldos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsReporteSueldosApplication {
	public static void main(String[] args) {
		SpringApplication.run(MsReporteSueldosApplication.class, args);
	}
}
