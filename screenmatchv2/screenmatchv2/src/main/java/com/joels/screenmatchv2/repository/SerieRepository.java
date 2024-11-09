package com.joels.screenmatchv2.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.joels.screenmatchv2.dto.EpisodeDTO;
import com.joels.screenmatchv2.model.Category;
import com.joels.screenmatchv2.model.Episode;
import com.joels.screenmatchv2.model.Serie;

public interface SerieRepository extends JpaRepository<Serie, Long> {

	Optional<Serie> findByTitleContainsIgnoreCase(String nameSerie);
	
	List<Serie> findTop5ByOrderByRatingDesc();
	
	List<Serie> findByGenre(Category category);
	
	@Query("SELECT s FROM Serie s WHERE s.totalSeasons <= :totalSeasons AND s.rating >= :rating")
	List<Serie> serieBySeasonAndRating(int totalSeasons, Double rating);
	
	@Query("SELECT e FROM Serie s JOIN s.episodes e WHERE e.title ILIKE %:nameEpisode%")
	List<Episode> episodeXName(String nameEpisode);
	
	@Query("SELECT e FROM Serie s JOIN s.episodes e WHERE s = :serie ORDER BY e.rating DESC LIMIT 5")
	List<Episode> top5Episodes(Serie serie);
	
	@Query("SELECT s FROM Serie s " + "JOIN s.episodes e " + "GROUP BY s " + "ORDER BY MAX(e.relaseDate) DESC LIMIT 5")
	List<Serie> mostRecentReleases();

	@Query("SELECT e FROM Serie s JOIN s.episodes e WHERE s.id = :id AND e.season = :seasonNumber")
	List<Episode> getSeasonByNumber(Long id, Long seasonNumber);
}
