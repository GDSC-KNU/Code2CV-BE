package team.gdsc.code2cv.global.client.github.response.detail;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnySetter;

public class GithubRepoLanguageInfoModel {
	private Map<String, Integer> languages = new HashMap<>();

	@JsonAnySetter
	public void setLanguage(String key, Integer value) {
		this.languages.put(key, value);
	}

	public Map<String, Integer> getLanguages() {
		return languages;
	}
}
