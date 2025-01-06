package com.tecno3f.screenmatch.model;

import jakarta.persistence.*;

import java.time.DateTimeException;
import java.time.LocalDate;

@Entity
@Table(name = "episodio")
public class Episodio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer temporada;
    private String titulo;
    private Integer numeroEpisodio;
    private Double puntuacionImdb;
    private LocalDate fechaLanzamiento;
    @ManyToOne
    private Serie serie;

    public Episodio(){}

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
    public Long getId() {
        return id;
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
    public String getNombreSerie(){
        return serie.getTitulo();
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
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
