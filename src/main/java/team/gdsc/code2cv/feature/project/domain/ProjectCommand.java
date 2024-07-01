package team.gdsc.code2cv.feature.project.domain;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import team.gdsc.code2cv.core.utils.SelfValidating;

public class ProjectCommand {

	@Getter
	@Builder
	public static class Create extends SelfValidating<ProjectCommand.Create> {
		@NotNull
		private final Long repoId;
		@NotNull
		private final String repoName;
		private final String description;
		@NotNull
		private final String htmlUrl;
		@Min(0)
		private final int forksCount;
		@Min(0)
		private final int starsCount;
		@NotNull
		private final LocalDateTime repoCreatedAt;
		@NotNull
		private final LocalDateTime repoUpdatedAt;
		private final String language;

		public Create(Long repoId, String repoName, String description, String htmlUrl, int forksCount,
			int starsCount,
			LocalDateTime repoCreatedAt, LocalDateTime repoUpdatedAt, String language) {
			this.repoId = repoId;
			this.repoName = repoName;
			this.description = description;
			this.htmlUrl = htmlUrl;
			this.forksCount = forksCount;
			this.starsCount = starsCount;
			this.repoCreatedAt = repoCreatedAt;
			this.repoUpdatedAt = repoUpdatedAt;
			this.language = language;
			this.validateSelf();
		}
	}


	@Getter
	@Builder
	public static class Update extends SelfValidating<ProjectCommand.Update> {
		@NotNull
		private final String repoName;
		private final String description;
		@NotNull
		private final String htmlUrl;
		@Min(0)
		private final int forksCount;
		@Min(0)
		private final int starsCount;
		@NotNull
		private final LocalDateTime repoUpdatedAt;
		private final String language;

		public Update(String repoName, String description, String htmlUrl, int forksCount, int starsCount,
			LocalDateTime repoUpdatedAt, String language) {
			this.repoName = repoName;
			this.description = description;
			this.htmlUrl = htmlUrl;
			this.forksCount = forksCount;
			this.starsCount = starsCount;
			this.repoUpdatedAt = repoUpdatedAt;
			this.language = language;
			this.validateSelf();
		}
	}
}
