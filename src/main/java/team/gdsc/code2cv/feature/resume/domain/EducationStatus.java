package team.gdsc.code2cv.feature.resume.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EducationStatus {
	GRADUATED("졸업"),
	ENROLLMENT("재학"),
	DROPOUT("중퇴"),
	GRADUATION_EXPECTED("졸업예정");

	private final String value;

}
