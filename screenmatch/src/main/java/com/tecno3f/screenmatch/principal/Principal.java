package com.tecno3f.screenmatch.principal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tecno3f.screenmatch.model.DataEpisodio;
import com.tecno3f.screenmatch.model.DataSerie;
import com.tecno3f.screenmatch.model.DataTemporada;
import com.tecno3f.screenmatch.model.Episodio;
import com.tecno3f.screenmatch.service.ConsumoAPI;
import com.tecno3f.screenmatch.service.ConversionDatos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    //PRIMER REFACTOR

    private Scanner sc = new Scanner(System.in);
    private String busqueda;
    private String json;
    final String base_url = "https://www.omdbapi.com/?t=";
    final String api_Key = "&apikey=c42a82a5";
    final String query = "&Season=";
    private ConversionDatos conversor = new ConversionDatos();

    public void mostrarMenu() throws JsonProcessingException {
        //BUSQUEDA SERIE
        System.out.println("Ingrese nombre la serie: ");
        busqueda = sc.next();
        json = new ConsumoAPI().obtenerDatos(base_url + busqueda.replace(" ", "%20")+api_Key);
        DataSerie dataSerie = conversor.obtenerDatos(json, DataSerie.class);
        System.out.println("DATOS DE "+ busqueda.toUpperCase());
        System.out.println(dataSerie);

        //BUSQUEDA TEMPORADAS
        ArrayList<DataTemporada> temporadas = new ArrayList<>();

        for(int i = 1; i<= Integer.valueOf(dataSerie.totalTemporadas() ); i++){
            json = new ConsumoAPI().obtenerDatos(base_url + busqueda.replace(" ", "%20")+query+i+api_Key);
            DataTemporada dataTemporada = conversor.obtenerDatos(json, DataTemporada.class);
            temporadas.add(dataTemporada);
        }
        temporadas.forEach(System.out::println);

        //MOSTRAR LOS TITULOS DE CADA TEMPORADA
        temporadas.forEach( t -> t.episodios().forEach(e-> System.out.println(e.titulo())));

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

        //Convirtiendo los datos a una lista de tipo Episodio
        List<Episodio> episodios = temporadas.stream()
                .flatMap(temp -> temp.episodios().stream()
                        .map(episodio -> new Episodio(temp.numeroTemprada(), episodio) ))
                .collect(Collectors.toList());
        episodios.forEach(System.out::println);
    }
}
