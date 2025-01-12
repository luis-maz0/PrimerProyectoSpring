package com.tecno3f.screenmatch.controller;

import com.tecno3f.screenmatch.Repository.SerieRepository;
import com.tecno3f.screenmatch.dto.EpisodioDTO;
import com.tecno3f.screenmatch.dto.SerieDTO;
import com.tecno3f.screenmatch.model.Serie;
import com.tecno3f.screenmatch.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/series")
public class SerieController {

    @Autowired
    private SerieService servicio;

    @GetMapping()
    public List<SerieDTO> obtenerSeries(){
        return servicio.obtenerSeries();
    }

    @GetMapping("/top5")
    public List<SerieDTO> obtenerTop5(){
        return servicio.obtenerTop5();
    }
    @GetMapping("/lanzamientos")
    public List<SerieDTO> obtenerLanzamientosMasRecientes(){
        return servicio.obtenerLanzamientosRecientes();
    }
    //Parametros dinámicos
    @GetMapping("/{id}")
    public SerieDTO obtenerPorId(@PathVariable Long id){
        return servicio.obtenerPorId(id);
    }
    @GetMapping("/{id}/temporadas/{numeroTemporada}")
    public List<EpisodioDTO> obtenerTemporadasPorNumero(@PathVariable Long id, @PathVariable Long numeroTemporada){
        return servicio.obtenerTemporadasPorNumero(id,numeroTemporada);
    }
    @GetMapping("/{id}/temporadas/todas")
    public List<EpisodioDTO> obtenerTodasLasTemporadas(@PathVariable Long id){
        return servicio.obtenerTodasLasTemporadas(id);
    }
    @GetMapping("/categoria/{nombreGenero}")
    public List<SerieDTO> obtenerSeriesPorCategoria(@PathVariable String nombreGenero){
        return servicio.obtenerSeriesPorCategoria(nombreGenero);
    }

}
