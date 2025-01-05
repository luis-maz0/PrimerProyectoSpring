package com.tecno3f.screenmatch.Repository;

import com.tecno3f.screenmatch.model.Categoria;
import com.tecno3f.screenmatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SerieRepository extends JpaRepository<Serie,Long> {
    Optional<Serie> findByTituloContainsIgnoreCase(String tituloSerie);
    List<Serie> findTop5ByOrderByPuntuacionDesc();
    List<Serie> findByGenero(Categoria categoria);
    List<Serie> findByTotalTemporadasGreaterThanEqualAndPuntuacionGreaterThanEqual(Integer temporadas, Double puntuacion);
}
