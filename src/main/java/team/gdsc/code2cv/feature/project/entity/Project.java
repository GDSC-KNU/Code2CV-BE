package team.gdsc.code2cv.feature.project.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.gdsc.code2cv.feature.project.domain.ProjectCommand;
import team.gdsc.code2cv.feature.user.entity.User;

@Builder
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Project {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	private Long repoId;
	private String repoName;
	private String description;
	private String htmlUrl;
	private Integer forksCount;
	private Integer starsCount;
	private LocalDateTime repoCreatedAt;
	private LocalDateTime repoUpdatedAt;
	private String language;



	public String getRepo(){
		return htmlUrl.split("/")[4];
	}

	public String getOwner(){
		return htmlUrl.split("/")[3];
	}

	public static Project create(ProjectCommand.Create command, User user){
		return Project.builder()
			.user(user)
			.repoId(command.getRepoId())
			.repoName(command.getRepoName())
			.description(command.getDescription())
			.htmlUrl(command.getHtmlUrl())
			.forksCount(command.getForksCount())
			.starsCount(command.getStarsCount())
			.repoCreatedAt(command.getRepoCreatedAt())
			.repoUpdatedAt(command.getRepoUpdatedAt())
			.language(command.getLanguage())
			.build();
	}

	public void update(ProjectCommand.Update command){
		this.repoName = command.getRepoName();
		this.description = command.getDescription();
		this.htmlUrl = command.getHtmlUrl();
		this.forksCount = command.getForksCount();
		this.starsCount = command.getStarsCount();
		this.repoUpdatedAt = command.getRepoUpdatedAt();
		this.language = command.getLanguage();
	}
}
