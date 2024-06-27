package team.gdsc.code2cv.feature.resume.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Embeddable
@Builder
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class Education {
	private Integer index;
	private String startDate;
	private String endDate;
	private String school;
	private String major;
	private EducationStatus status;
}
