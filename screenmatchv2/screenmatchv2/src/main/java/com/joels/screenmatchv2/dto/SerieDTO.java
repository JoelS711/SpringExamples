package com.joels.screenmatchv2.dto;

import com.joels.screenmatchv2.model.Category;



public record SerieDTO(
		 String title,
		 Integer totalSeasons,
		 Double rating,
		 String poster,
		 Category genre,
		 String actors,
		 String sypnosis
		) {

}
