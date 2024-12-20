package com.tecno3f.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataSerie(
        //TODO: Agregar: Genero, Sinopsis, Poster y Actores.
        @JsonAlias("Title") String titulo,
        @JsonAlias("Year") String anio,
        @JsonAlias("imdbRating") String puntuacion,
        @JsonAlias("totalSeasons") String totalTemporadas) {
}
