package team.gdsc.code2cv.feature.project.repository;

import java.util.List;

public interface GithubRepoClient {
	List<GithubRepoApiResponse> getAllRepositoriesInfo(String githubToken);
}
