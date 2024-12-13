package com.tecno3f.screenmatch;

import com.tecno3f.screenmatch.model.DataEpisodio;
import com.tecno3f.screenmatch.model.DataSerie;
import com.tecno3f.screenmatch.model.DataTemporada;
import com.tecno3f.screenmatch.service.ConsumoAPI;
import com.tecno3f.screenmatch.service.ConversionDatos;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String busqueda = "Game+of+thrones";
		String urlAPI = "https://www.omdbapi.com/?i=tt3896198&apikey=c42a82a5&t=".concat(busqueda);
		String json = new ConsumoAPI().obtenerDatos(urlAPI);

		System.out.println(json);
		ConversionDatos conversor = new ConversionDatos();

		DataSerie datosSerie = conversor.obtenerDatos(json, DataSerie.class);
		System.out.println(datosSerie);

		//Datos episodio de determinada temporada
		//TODO: Ver documentación. Cambio de url de la API para consultar temporadas & episodios.
		urlAPI = "https://www.omdbapi.com/?t=Game%20of%20Thrones&Season=1&Episode=1&apikey=c42a82a5";
		json = new ConsumoAPI().obtenerDatos(urlAPI);
		DataEpisodio datosEpisodio = conversor.obtenerDatos(json, DataEpisodio.class);
		System.out.println(datosEpisodio);

		//Ver todas las temporadas
		ArrayList<DataTemporada> temporadas = new ArrayList<>();

		for (int i = 1; i <= Integer.valueOf(datosSerie.totalTemporadas()); i++) {
			json = new ConsumoAPI().obtenerDatos("https://www.omdbapi.com/?t=Game%20of%20Thrones&Season="+i+"&apikey=c42a82a5");
			DataTemporada datosTemporada = conversor.obtenerDatos(json,DataTemporada.class);
			temporadas.add(datosTemporada);
		}
		temporadas.forEach(System.out::println);

		//Lista de datos de cada episodio
		List<DataEpisodio> listaEpisodios = temporadas.stream()
				.flatMap( temp -> temp.episodios().stream())
				.collect(Collectors.toList());
		System.out.println( listaEpisodios );

		//Filtrar top 10 mejores episodios
		System.out.println("----------------TOP 10 Mejores episodios------------------");
		listaEpisodios.stream()
				.filter( episode -> !episode.puntuacionImdb().equalsIgnoreCase("N/A"))
				.sorted(Comparator.comparing(DataEpisodio::puntuacionImdb).reversed())
				.limit(10)
				.forEach(System.out::println);
	}
}
