package team.gdsc.code2cv.feature.resume.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import team.gdsc.code2cv.core.utils.SelfValidating;

public class ResumeCommand {

	@Getter
	@Builder
	public static class CreateByNew extends SelfValidating<CreateByNew> {
		@NotBlank(message = "이름은 필수입니다.")
		private final String name;
		@NotNull
		private final Boolean isDone;
		@NotNull
		private final Boolean isDefault;

		public CreateByNew(String name, Boolean isDone, Boolean isDefault) {
			this.name = name;
			this.isDone = isDone;
			this.isDefault = isDefault;
			this.validateSelf();
		}
	}

	@Getter
	@Builder
	public static class CreateByUpload extends SelfValidating<CreateByUpload> {
		@NotBlank(message = "이름은 필수입니다.")
		private final String name;
		@NotNull
		private final Boolean isDefault;
		@NotBlank(message = "파일은 필수입니다.")
		// TODO: 파일 업로드 처리
		private final String file;

		public CreateByUpload(String name, Boolean isDefault, String file) {
			this.name = name;
			this.isDefault = isDefault;
			this.file = file;
			this.validateSelf();
		}
	}
}
