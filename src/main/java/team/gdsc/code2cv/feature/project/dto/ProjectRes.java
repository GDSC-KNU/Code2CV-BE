package team.gdsc.code2cv.feature.project.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import team.gdsc.code2cv.feature.project.entity.Project;

public class ProjectRes {
	@Builder
	public record ProjectDto(
		Long id,
		String projectUrl,
		String projectName,
		Integer starsCount,
		Integer forksCount,
		LocalDateTime startAt,
		LocalDateTime updatedAt,
		String language
	) {
		public static ProjectDto from(Project project){
			return ProjectDto.builder()
				.id(project.getId())
				.projectUrl(project.getHtmlUrl())
				.projectName(project.getRepoName())
				.starsCount(project.getStarsCount())
				.forksCount(project.getForksCount())
				.startAt(project.getRepoCreatedAt())
				.updatedAt(project.getRepoUpdatedAt())
				.language(project.getLanguage())
				.build();
		}
	}
}
