package team.gdsc.code2cv.feature.project.repository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonProperty;

import team.gdsc.code2cv.feature.project.domain.ProjectCommand;

public record GithubRepoApiResponse(
	Long id,
	String name,
	String description,
	@JsonProperty("html_url")
	String htmlUrl,
	@JsonProperty("created_at")
	String createdAt,
	@JsonProperty("updated_at")
	String updatedAt,
	@JsonProperty("forks_count")
	int forksCount,
	@JsonProperty("stargazers_count")
	int stargazersCount,
	String language
) {
	public ProjectCommand.Create toCreateCommand() {
		DateTimeFormatter koreanFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
			.withZone(ZoneId.of("Asia/Seoul"));
		return ProjectCommand.Create.builder()
			.repoId(id)
			.repoName(name)
			.description(description)
			.htmlUrl(htmlUrl)
			.forksCount(forksCount)
			.starsCount(stargazersCount)
			.repoCreatedAt(koreanFormatter.parse(createdAt, LocalDateTime::from))
			.repoUpdatedAt(koreanFormatter.parse(updatedAt, LocalDateTime::from))
			.language(language)
			.build();
	}

	public ProjectCommand.Update toUpdateCommand() {
		DateTimeFormatter koreanFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
			.withZone(ZoneId.of("Asia/Seoul"));
		return ProjectCommand.Update.builder()
			.repoName(name)
			.description(description)
			.htmlUrl(htmlUrl)
			.forksCount(forksCount)
			.starsCount(stargazersCount)
			.repoUpdatedAt(koreanFormatter.parse(updatedAt, LocalDateTime::from))
			.language(language)
			.build();
	}
}