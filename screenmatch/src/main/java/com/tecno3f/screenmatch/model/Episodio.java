package com.tecno3f.screenmatch.model;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Date;

public class Episodio {
    private Integer temporada;
    private String titulo;
    private Integer numeroEpisodio;
    private Double puntuacionImdb;
    private LocalDate fechaLanzamiento;

    public Episodio( String numTemp,DataEpisodio e) {
        this.temporada = Integer.valueOf(numTemp);
        this.titulo = e.titulo();
        this.numeroEpisodio = Integer.valueOf(e.episodio());
        try{
            this.puntuacionImdb = Double.valueOf(e.puntuacionImdb());
        }catch(NumberFormatException err){
            this.puntuacionImdb = 0.0;
        }
        try{
            this.fechaLanzamiento = LocalDate.parse(e.fechaLanzamiento());
        }catch (DateTimeException err){
            this.fechaLanzamiento = null; //Deuda tecnica
        }

    }

    public Integer getTemporada() {
        return temporada;
    }
    public String getTitulo() {
        return titulo;
    }
    public Integer getNumeroEpisodio() {
        return numeroEpisodio;
    }
    public Double getPuntuacionImdb() {
        return puntuacionImdb;
    }
    public LocalDate getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    @Override
    public String toString() {
        return "Episodio{" +
                "temporada=" + temporada +
                ", titulo='" + titulo + '\'' +
                ", numeroEpisodio=" + numeroEpisodio +
                ", puntuacionImdb=" + puntuacionImdb +
                ", fechaLanzamiento=" + fechaLanzamiento +
                '}';
    }
}
