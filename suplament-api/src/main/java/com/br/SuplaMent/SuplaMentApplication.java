package com.br.SuplaMent;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@EnableRabbit
@SpringBootApplication
public class SuplaMentApplication {
	public static void main(String[] args) {
		SpringApplication.run(SuplaMentApplication.class, args);
	}
}
