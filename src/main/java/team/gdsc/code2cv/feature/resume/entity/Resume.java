package team.gdsc.code2cv.feature.resume.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.gdsc.code2cv.feature.resume.domain.ResumeCommand;
import team.gdsc.code2cv.global.jwt.JwtUser;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@AllArgsConstructor
@Builder
public class Resume /*extends BaseTimeEntity*/ {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// @ManyToOne(fetch = FetchType.LAZY)
	// @JoinColumn(name = "user_id")
	private Long userId;

	private String name;

	private Boolean isDone;

	private Boolean isDefault;

	private String file;

	public static Resume create(ResumeCommand.CreateByNew command, JwtUser jwtUser) {
		return Resume.builder()
			.userId(jwtUser.getId())
			.name(command.getName())
			.isDone(command.getIsDone())
			.isDefault(command.getIsDefault())
			.file("")
			.build();

	}

	public static Resume create(ResumeCommand.CreateByUpload command, JwtUser jwtUser) {
		return Resume.builder()
			.userId(jwtUser.getId())
			.name(command.getName())
			.isDone(true)
			.isDefault(command.getIsDefault())
			.file(command.getFile())
			.build();
	}
}
