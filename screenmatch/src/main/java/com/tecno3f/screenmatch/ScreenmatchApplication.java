package com.tecno3f.screenmatch;

import com.tecno3f.screenmatch.service.ConsumoAPI;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String busqueda = "Game of thrones";
		String urlAPI = "https://www.omdbapi.com/?i=tt3896198&apikey=c42a82a5&t="+busqueda;
		String json = new ConsumoAPI().obtenerDatos(urlAPI);
		System.out.println(json);
	}
}
