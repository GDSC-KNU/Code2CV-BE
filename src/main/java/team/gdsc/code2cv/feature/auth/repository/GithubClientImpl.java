package team.gdsc.code2cv.feature.auth.repository;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import team.gdsc.code2cv.core.exception.ExternalServerCommunicationException;
import team.gdsc.code2cv.core.exception.TokenExpiredException;
import team.gdsc.code2cv.feature.user.domain.GithubAccount;

@Component
public class GithubClientImpl implements GithubClient{
	@Value("${spring.security.oauth2.client.registration.github.client-id}")
	private String clientId;
	@Value("${spring.security.oauth2.client.registration.github.client-secret}")
	private String clientSecret;

	private final RestClient restClient;

	public GithubClientImpl(ClientHttpRequestFactory clientHttpRequestFactory) {
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

	/**
	 * 1. code, state를 통해 Github API로부터 access token을 받아온다.
	 * 2. access token을 이용하여 Github API로부터 사용자 정보를 받아온다.
	 */
	@Override
	public String getGithubAccessToken(String code, String state) {
		String uri = genRequestUri(code, state);
		String response = restClient.post()
			.uri(uri)
			.retrieve()
			.body(String.class);
		assert response != null;
		return parseAccessToken(response);
	}

	/**
	 * Github API로부터 사용자 정보를 받아온다.
	 */
	@Override
	public GithubAccount getGithubAccountResponse(String githubAccessToken) {
		var dto =  restClient.get()
			.uri("https://api.github.com/user")
			.header("Authorization", "token " + githubAccessToken)
			.retrieve()
			.body(GithubRes.GithubAccountDto.class);
		assert dto != null;
		return dto.toDomain(githubAccessToken);
	}

	private String genRequestUri(String code, String state) {
		return "https://github.com/login/oauth/access_token?client_id="
			+ clientId
			+ "&client_secret="
			+ clientSecret
			+ "&code="
			+ code
			+ "&state="
			+ state;
	}

	private String parseAccessToken(String response) {
		return Arrays.stream(response.split("&")).toList()
			.stream()
			.filter(it -> it.startsWith("access_token="))
			.findFirst()
			.map(it -> it.split("=")[1])
			.orElseThrow(() -> new ExternalServerCommunicationException("Github API 호출 중 에러가 발생했습니다."));
	}
}
