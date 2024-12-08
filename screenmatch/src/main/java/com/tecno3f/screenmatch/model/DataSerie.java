package com.tecno3f.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DataSerie(
        @JsonAlias("Title") String titulo,
        @JsonAlias("Year") String anio,
        @JsonAlias("imdbRating") Double puntuacion,
        @JsonAlias("totalSeasons") Integer totalTemporadas) {
}
