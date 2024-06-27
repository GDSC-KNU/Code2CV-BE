package team.gdsc.code2cv.feature.project.repository;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import team.gdsc.code2cv.global.client.github.GithubRestApiClient;
import team.gdsc.code2cv.global.client.github.response.detail.GithubCommitInfoModel;
import team.gdsc.code2cv.global.client.github.response.detail.GithubRepoLanguageInfoModel;
import team.gdsc.code2cv.global.client.github.response.GithubRepositoryInfoModel;

/**
 * Github API를 호출하여 GithubRepository 정보를 가져오는 클래스
 * Repository에 대한 정보를 가져오는 API 호출을 담당한다.
 */
@Repository
@RequiredArgsConstructor
public class GithubRepositoryClient {
	private final GithubRestApiClient githubRestApiClient;

	/**
	 * Github API [getRepositoryList]를 이용하여 사용자의 모든 Repository 정보를 가져온다.
	 * 다음 페이지가 없을 때까지 모든 페이지를 동기방식으로 가져와 List로 반환한다.
	 */
	public List<GithubRepositoryInfoModel> getAllRepositoriesInfo(String token) {
		return getRepositoriesInfoAllPageStream(token)
			.flatMap(List::stream)
			.toList();
	}

	/**
	 * Github API [getRepositoryList]를 이용하여 사용자의 모든 Repository 정보를 가져온다.
	 * 다음 페이지가 없을 때까지 모든 페이지를 가져오는 작업을 Stream으로 처리한다.
	 * 호출하는 곳에서는 Stream을 통해 데이터 처리를 향상 시킬 수 있다.
	 */
	public Stream<List<GithubRepositoryInfoModel>> getRepositoriesInfoAllPageStream(String token) {
		return Stream.iterate(1, page -> page + 1)
			.map(currentPage -> githubRestApiClient.getRepositoryList(
				"Bearer " + token, currentPage,
				30, "updated",
				null, null,
				"public", "owner,collaborator"
			))
			.map(HttpEntity::getBody)
			.takeWhile(response -> !response.isEmpty());
	}

	/*
	 * 프로젝트의 사용 언어를 가져온다.
	 * https://api.github.com/repos/GDSC-KNU/Code2CV-BE/languages
	 * Authorization: Bearer token
	 */
	public GithubRepoLanguageInfoModel getRepositoryLanguages(String token, String owner, String repo) {
		return githubRestApiClient.getRepositoryLanguages("Bearer " + token, owner, repo).getBody();
	}
	/*
	 * 페이징된 커밋정보를 가져온다.
	 * 프레임워크 분석,기여자 분석,담당한 구현,사용한 라이브러리 분석 메서드를 위한 정보를 담고 있다.
	 * 페이지
	 */
	public Stream<List<GithubCommitInfoModel>> getRepositoryCommitsAllPageStream(String token, String owner, String repo) {
		return Stream.iterate(1, page -> page + 1)
			.map(currentPage -> githubRestApiClient.getRepositoryCommits(
				"Bearer " + token, owner, repo, currentPage,
				30, null, null
			))
			.map(HttpEntity::getBody)
			.takeWhile(response -> !response.isEmpty());
	}
	/*
	 * 프로젝트의 커밋의 변경 파일 목록을 가져온다.
	 */



}
