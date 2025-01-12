package com.tecno3f.screenmatch.service;

import com.tecno3f.screenmatch.Repository.SerieRepository;
import com.tecno3f.screenmatch.dto.SerieDTO;
import com.tecno3f.screenmatch.model.Serie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SerieService {
    @Autowired
    private SerieRepository repository;

    public List<SerieDTO> obtenerSeries(){
        return repository.findAll().stream()
                .map( serie -> new SerieDTO(
                        serie.getId(),
                        serie.getTitulo(),
                        serie.getTotalTemporadas(),
                        serie.getPuntuacion(),
                        serie.getPoster(),
                        serie.getGenero(),
                        serie.getActores(),
                        serie.getSinopsis(),
                        serie.getAnio()
                ))
                .toList();
    }

    public List<SerieDTO> obtenerTop5() {
        return repository.findTop5ByOrderByPuntuacionDesc().stream()
                .map( serie -> new SerieDTO(
                        serie.getId(),
                        serie.getTitulo(),
                        serie.getTotalTemporadas(),
                        serie.getPuntuacion(),
                        serie.getPoster(),
                        serie.getGenero(),
                        serie.getActores(),
                        serie.getSinopsis(),
                        serie.getAnio()
                ))
                .toList();
    }
    public List<SerieDTO> obtenerLanzamientosRecientes(){
        return repository.estrenosRecientes().stream()
                .map( serie -> new SerieDTO(
                        serie.getId(),
                        serie.getTitulo(),
                        serie.getTotalTemporadas(),
                        serie.getPuntuacion(),
                        serie.getPoster(),
                        serie.getGenero(),
                        serie.getActores(),
                        serie.getSinopsis(),
                        serie.getAnio()))
                .toList();
    }

    public SerieDTO obtenerPorId(Long id) {
        Optional<Serie> serie = repository.findById(id);
        if(serie.isPresent()){
            Serie s = serie.get();
            return new SerieDTO(
                    s.getId(),
                    s.getTitulo(),
                    s.getTotalTemporadas(),
                    s.getPuntuacion(),
                    s.getPoster(),
                    s.getGenero(),
                    s.getActores(),
                    s.getSinopsis(),
                    s.getAnio());
        }else{
            return null;
        }
    }
}
