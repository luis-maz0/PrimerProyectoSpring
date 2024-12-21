package com.tecno3f.screenmatch.principal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tecno3f.screenmatch.model.DataEpisodio;
import com.tecno3f.screenmatch.model.DataSerie;
import com.tecno3f.screenmatch.model.DataTemporada;
import com.tecno3f.screenmatch.model.Episodio;
import com.tecno3f.screenmatch.service.ConsumoAPI;
import com.tecno3f.screenmatch.service.ConversionDatos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private Scanner sc = new Scanner(System.in);
    private String busqueda;
    private String json;
    private final String BASE_URL = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=c42a82a5";
    private final String QUERY = "&Season=";
    private ConversionDatos conversor = new ConversionDatos();
    private ArrayList<DataSerie> seriesBuscadas = new ArrayList<>();

    public void mostrarMenu() throws JsonProcessingException {
        int opcion = -1;
        String menu = """
                ---------------------
                | 1. Buscar series
                | 2. Buscar episodios
                | 3. Mostrar series buscadas
                | 0. Salir
                ---------------------
                """;
        do {
            System.out.println(menu);
            opcion = Integer.parseInt(sc.next());
            sc.nextLine();
            switch (opcion) {
                case 0:
                    System.out.println("Adios!");
                    break;
                case 1:
                    System.out.println("**** Buscando serie **** ");
                    this.buscarSerie();
                    break;
                case 2:
                    System.out.println("**** Buscando episodio ****");
                    this.mostrarTodosEpisodiosSerie();
                    break;
                case 3:
                    System.out.println("**** Series buscadas ****");
                    this.mostrarSeriesBuscadas();
                    break;
                default:
                    System.out.println("Opción invalida");
                    break;
            }
        } while (opcion != 0);
    }

    public DataSerie getDataSerie() throws JsonProcessingException {
        System.out.println("Ingrese nombre de la serie: ");
        busqueda = sc.next();
        sc.nextLine();
        json = new ConsumoAPI().obtenerDatos(BASE_URL + busqueda.replace(" ", "+") + API_KEY);
        return conversor.obtenerDatos(json, DataSerie.class);
    }

    public void mostrarTodosEpisodiosSerie() throws JsonProcessingException {
        DataSerie dataSerie = this.getDataSerie();
        ArrayList<DataTemporada> temporadas = new ArrayList<>();

        for (int i = 1; i <= Integer.parseInt(dataSerie.totalTemporadas()); i++) {
            json = new ConsumoAPI().obtenerDatos(BASE_URL + busqueda.replace(" ", "+") + QUERY + i + API_KEY);
            DataTemporada dataTemporada = conversor.obtenerDatos(json, DataTemporada.class);
            temporadas.add(dataTemporada);
        }
        temporadas.forEach(System.out::println);
    }

    public void buscarSerie() throws JsonProcessingException {
        DataSerie dataSerie = this.getDataSerie();
        seriesBuscadas.add(dataSerie);
        System.out.println(dataSerie);
    }

    public void mostrarSeriesBuscadas() {
        seriesBuscadas.forEach(System.out::println);
    }
}
/*
        //MOSTRAR LOS TITULOS DE CADA TEMPORADA
        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));

        //Lista de datos de cada episodio
        List<DataEpisodio> listaEpisodios = temporadas.stream()
                .flatMap(temp -> temp.episodios().stream())
                .collect(Collectors.toList());
        System.out.println(listaEpisodios);

        //Filtrar top 10 mejores episodios
        System.out.println("----------------TOP 10 Mejores episodios------------------");
        listaEpisodios.stream()
                .filter(episode -> !episode.puntuacionImdb().equalsIgnoreCase("N/A"))
                .peek(e -> System.out.println("Filtro " + e))
                .sorted(Comparator.comparing(DataEpisodio::puntuacionImdb).reversed())
                .peek(e -> System.out.println("Orden " + e))
                .limit(10)
                .peek(e -> System.out.println("Limite" + e))
                .forEach(System.out::println);

        //Convirtiendo los datos a una lista de tipo Episodio
        System.out.println("---------------TODOS LOS EPISODIOS-------------");
        List<Episodio> episodios = temporadas.stream()
                .flatMap(temp -> temp.episodios().stream()
                        .map(episodio -> new Episodio(temp.numeroTemprada(), episodio)))
                .collect(Collectors.toList());
        episodios.forEach(System.out::println);

        //Filtrando episodios por fecha
        System.out.println("------------POR FECHA---------------");
        System.out.println("Ingrese año: ");
        Scanner sc = new Scanner(System.in);
        Integer fecha = sc.nextInt();

        LocalDate fechaBusqueda = LocalDate.of(fecha, 1, 1);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        episodios.stream()
                .filter(e -> e.getFechaLanzamiento() != null && e.getFechaLanzamiento().isAfter(fechaBusqueda))
                .forEach(e -> System.out.println(
                        "Temporada: " + e.getTemporada() +
                                "\nEpisodio: " + e.getNumeroEpisodio() +
                                "\nFechaLanzamiento: " + e.getFechaLanzamiento().format(dtf)
                ));
        //Busqueda episodio por titulo
        System.out.println("Ingrese el nombre del titulo del espisodio: ");
        String nombreEpisodioBuscado = sc.next();

        Optional<Episodio> episodioEncontrado = episodios.stream()
                .filter(e -> e.getTitulo().toLowerCase().contains(nombreEpisodioBuscado.toLowerCase()))
                .findFirst();
        if (episodioEncontrado.isPresent()) {
            System.out.println("Episodio encontrado" + episodioEncontrado.get());
        } else {
            System.out.println("NO SE ENCONTRO DICHO EPISODIO");
        }
        //Obtener promedio de temporadas
        Map<Integer, Double> promedioPuntuacionTemporadas = episodios.stream()
                .filter(e -> e.getPuntuacionImdb() > 0)
                .collect(Collectors.groupingBy(Episodio::getTemporada,
                        Collectors.averagingDouble(Episodio::getPuntuacionImdb)));
        System.out.println(promedioPuntuacionTemporadas);

        //Estadisticas
        DoubleSummaryStatistics estadisticasEpisodios = episodios.stream()
                .filter( e -> e.getPuntuacionImdb() > 0 )
                .collect(Collectors.summarizingDouble( Episodio::getPuntuacionImdb ));
        System.out.println( estadisticasEpisodios );
    */

