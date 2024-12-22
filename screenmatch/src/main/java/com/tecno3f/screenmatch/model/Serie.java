package com.tecno3f.screenmatch.model;
import java.util.OptionalDouble;

public class Serie {
    private String titulo;
    private String anio;
    private Double puntuacion;
    private Integer totalTemporadas;
    private String poster;
    private Categoria genero;
    private String actores;
    private String sinopsis;

    public Serie(DataSerie datosSerie) {
        this.titulo = datosSerie.titulo();
        this.anio = datosSerie.anio();
        this.puntuacion = OptionalDouble.of( Double.parseDouble( datosSerie.puntuacion() )).orElse(0);
        //Puede que no haya temporadas porque es una pelicula -> N/A
        this.totalTemporadas = Integer.valueOf(datosSerie.totalTemporadas());
        this.poster = datosSerie.poster();
        //comedia,drama,accion -> Elige el primero y saca espacio en blanco.
        this.genero = Categoria.fromString( datosSerie.genero().split(",")[0].trim());
        this.actores = datosSerie.actores();
        this.sinopsis = datosSerie.sinopsis();
    }

    public Categoria getGenero() {
        return genero;
    }

    @Override
    public String toString() {
        return "Serie{" +
                ", genero=" + genero +
                "titulo='" + titulo + '\'' +
                ", anio='" + anio + '\'' +
                ", puntuacion=" + puntuacion +
                ", totalTemporadas=" + totalTemporadas +
                ", poster='" + poster + '\'' +
                ", actores='" + actores + '\'' +
                ", sinopsis='" + sinopsis + '\'' +
                '}';
    }
}