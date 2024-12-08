package com.tecno3f.screenmatch;

import com.tecno3f.screenmatch.model.DataSerie;
import com.tecno3f.screenmatch.service.ConsumoAPI;
import com.tecno3f.screenmatch.service.ConversionDatos;
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
		String busqueda = "matrix";
		String urlAPI = "https://www.omdbapi.com/?i=tt3896198&apikey=c42a82a5&t="+busqueda;
		String json = new ConsumoAPI().obtenerDatos(urlAPI);

		System.out.println(json);
		ConversionDatos conversor = new ConversionDatos();

		DataSerie datosSerie = conversor.obtenerDatos(json, DataSerie.class);

		System.out.println(datosSerie);
	}
}
