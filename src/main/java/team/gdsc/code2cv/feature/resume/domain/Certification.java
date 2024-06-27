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
public class Certification {
	private Integer index;
	private String name;
	private String date;
	private String organization;
}
