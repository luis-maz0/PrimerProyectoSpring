package com.tecno3f.screenmatch.dto;

import com.tecno3f.screenmatch.model.Categoria;

public record SerieDTO(
        Long id,
        String titulo,
        Integer totalTemporadas,
        Double puntuacion,
        String poster,
        Categoria genero,
        String actores,
        String sinopsis,
        String anio
){}
