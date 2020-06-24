package br.com.pautaservice;

import org.apache.ibatis.annotations.Mapper;

import org.mybatis.spring.annotation.MapperScan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "br.com.pauta")
@MapperScan(value = "br.com.pauta", annotationClass = Mapper.class)
public class PautaServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(PautaServiceApplication.class, args);
	}
}