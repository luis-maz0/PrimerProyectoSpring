package com.tecno3f.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataTemporada(
        @JsonAlias("Season") String numeroTemprada,
        @JsonAlias("Episodes") List<DataEpisodio> episodios
){}
