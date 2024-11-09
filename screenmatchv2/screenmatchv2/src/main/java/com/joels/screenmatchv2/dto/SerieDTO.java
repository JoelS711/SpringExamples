package com.joels.screenmatchv2.dto;

import com.joels.screenmatchv2.model.Category;

public record SerieDTO(Long id, String title, Integer totalSeasons, Double rating, String poster, Category genre,
		String actors, String synopsis) {

}
