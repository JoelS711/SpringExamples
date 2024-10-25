package model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataBook(@JsonAlias("title") String title,@JsonAlias("authors") List<AuthorInfo> author,@JsonAlias("languages") List<String> language,@JsonAlias("download_count") Double downloadsCount) {

}
