package team.gdsc.code2cv.feature.resume.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
enum CareerType {
	PART_TIME("알바"),
	FREELANCE("프리랜서"),
	INTERN("인턴"),
	WORKER("직원"),
	LEADER("팀장"),
	EXECUTIVE("임원"),
	CEO("대표");

	private final String value;
}