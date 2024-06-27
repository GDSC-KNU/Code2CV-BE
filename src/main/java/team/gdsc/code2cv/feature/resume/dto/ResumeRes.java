package team.gdsc.code2cv.feature.resume.dto;

import lombok.Builder;
import team.gdsc.code2cv.feature.resume.entity.Resume;

public class ResumeRes {
	@Builder
	public record ResumeDto(
		Long id,
		String name,
		Boolean isDone,
		Boolean isDefault,
		String file
	) {
		public static ResumeDto from(Resume resume) {
			return ResumeDto.builder()
				.id(resume.getId())
				.name(resume.getName())
				.isDone(resume.getIsDone())
				.isDefault(resume.getIsDefault())
				.file(resume.getFile())
				.build();
		}
	}
}
