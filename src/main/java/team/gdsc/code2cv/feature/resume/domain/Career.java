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
public class Career {
	private Integer index;
	private String startDate;
	private String endDate;
	private String title;
	private String content;
	private Boolean isWorking;
	private CareerType type;
}