package com.tecno3f.screenmatch.Repository;

import com.tecno3f.screenmatch.dto.EpisodioDTO;
import com.tecno3f.screenmatch.model.Categoria;
import com.tecno3f.screenmatch.model.Episodio;
import com.tecno3f.screenmatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SerieRepository extends JpaRepository<Serie,Long> {
    Optional<Serie> findByTituloContainsIgnoreCase(String tituloSerie);
    List<Serie> findTop5ByOrderByPuntuacionDesc();
    List<Serie> findByGenero(Categoria categoria);
    List<Serie> findByTotalTemporadasGreaterThanEqualAndPuntuacionGreaterThanEqual(Integer temporadas, Double puntuacion);
//Query nativa
    @Query("SELECT s FROM Serie s WHERE s.totalTemporadas >= :temporadas AND s.puntuacion >= :puntuacion")
    List <Serie> seriesPorTemporadaYPuntuacion(Integer temporadas, Double puntuacion);

    @Query("SELECT e FROM Episodio e JOIN e.serie s WHERE e.titulo ILIKE %:nombreEpisodio%")
    List<Episodio> episodioPorNombre(String nombreEpisodio);

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE s = :serie ORDER BY e.puntuacionImdb DESC LIMIT 5")
    List<Episodio> top5Episodios(Serie serie);

    @Query("SELECT s FROM Serie s JOIN s.episodios e GROUP BY s ORDER BY MAX(e.fechaLanzamiento) DESC LIMIT 5")
    List<Serie> estrenosRecientes();

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE s.id = :id AND e.temporada = :numeroTemporada")
    List<Episodio> obtenerTemporadasPorNumero(Long id, Long numeroTemporada);
}
