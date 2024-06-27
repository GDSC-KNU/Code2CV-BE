package team.gdsc.code2cv.feature.project.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import team.gdsc.code2cv.feature.project.repository.ProjectAnalysisRepository;

@Service
@RequiredArgsConstructor
public class ProjectAnalysisService {
	ProjectAnalysisRepository projectAnalysisRepository;



	public void analyzeProject(String token, String projectUrl) {

	}

	// framework
	private void analyzeFramework() {

	}

	// libaray
	private void analyzeLibrary() {

	}

	// 담당한 구현
	private void analyzeImplementation() {

	}

	/* 내용
	 AI 호출로 최초 프로젝트 세부 내용 추가 필요/
	*/
	private void analyzeContent() {

	}

	/* 피드백 추가
	 AI 호출 필요
	 */
	private void addFeedback() {

	}

	// 사용된 언어
	// @JsonProperty("languages_url") String languagesUrl,
	private void analyzeLanguage() {

	}

	/* 기여자 분석
	@JsonProperty("contributors_url") String contributorsUrl,
	@JsonProperty("collaborators_url") String collaboratorsUrl,
	private void analyzeContributor() {
	*/
	}
	// TODO: 각 세부 메서드들이 병렬로 처리 될 수 있도록 해야함
}
