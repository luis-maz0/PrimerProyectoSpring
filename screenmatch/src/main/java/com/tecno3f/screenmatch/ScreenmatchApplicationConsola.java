/*
package com.tecno3f.screenmatch;

import com.tecno3f.screenmatch.Repository.SerieRepository;
import com.tecno3f.screenmatch.principal.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplicationConsola implements CommandLineRunner {
	@Autowired //Inyeccion de dependencias
	private SerieRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplicationConsola.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		Principal menu = new Principal(repository);
		menu.mostrarMenu();
	}
}
*/