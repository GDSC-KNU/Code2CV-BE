package team.gdsc.code2cv.feature.project.repository;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import lombok.RequiredArgsConstructor;
import team.gdsc.code2cv.core.exception.ExternalServerCommunicationException;
import team.gdsc.code2cv.core.exception.TokenExpiredException;

@Component
public class GithubRepoClientImpl implements GithubRepoClient{
	private final RestClient restClient;

	public GithubRepoClientImpl(ClientHttpRequestFactory clientHttpRequestFactory) {
		restClient = RestClient.builder()
			.requestFactory(clientHttpRequestFactory)
			.defaultStatusHandler(HttpStatusCode::is5xxServerError,
				(request, response) -> {
					throw new ExternalServerCommunicationException("Github API 호출 중 에러가 발생했습니다.");
				})
			.defaultStatusHandler(httpStatusCode -> httpStatusCode.value() == 401,
				(request, response) -> {
					throw new TokenExpiredException("Github API Token이 만료되었습니다.");
				})
			.build();
	}

	@Override
	public List<GithubRepoApiResponse> getAllRepositoriesInfo(String githubToken) {
		return restClient.get()
			.uri("https://api.github.com/user/repos?sort=updated")
			.header("Authorization", "Bearer " + githubToken)
			.retrieve()
			.body(new ParameterizedTypeReference<List<GithubRepoApiResponse>>() {});
	}
}
