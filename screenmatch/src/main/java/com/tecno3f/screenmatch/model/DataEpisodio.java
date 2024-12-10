package com.tecno3f.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataEpisodio(
        @JsonAlias("Title") String titulo,
        @JsonAlias("Released") String fechaLanzamiento,
        @JsonAlias("Episode") String episodio
) {
}
