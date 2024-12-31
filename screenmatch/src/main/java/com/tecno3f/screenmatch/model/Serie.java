package com.tecno3f.screenmatch.model;
import jakarta.persistence.*;

import java.util.List;
import java.util.OptionalDouble;

@Entity
@Table(name="series")
public class Serie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    private String anio;
    private Double puntuacion;
    private Integer totalTemporadas;
    private String poster;
    @Enumerated(EnumType.STRING)
    private Categoria genero;
    private String actores;
    private String sinopsis;
    @OneToMany(mappedBy = "serie")
    private List<Episodio> episodios;

    public Serie(){};

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Serie{" +
                "genero=" + genero +
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