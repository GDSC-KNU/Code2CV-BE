package team.gdsc.code2cv.global.client.github.response.detail;

public record GithubCommitInfoModel(
	String sha,
	String node_id,
	Commit commit,
	String url,
	String html_url,
	String comments_url,
	Author author,
	Committer committer,
	Parent[] parents
) {
	public record Commit(
		Author author,
		Committer committer,
		String message,
		Tree tree,
		String url,
		int comment_count,
		Verification verification
	) {
		public record Author(
			String name,
			String email,
			String date
		) {
		}

		public record Committer(
			String name,
			String email,
			String date
		) {
		}

		public record Tree(
			String sha,
			String url
		) {
		}

		public record Verification(
			boolean verified,
			String reason,
			String signature,
			String payload
		) {
		}
	}

	public record Author(
		String login,
		int id,
		String node_id,
		String avatar_url,
		String gravatar_id,
		String url,
		String html_url,
		String followers_url,
		String following_url,
		String gists_url,
		String starred_url,
		String subscriptions_url,
		String organizations_url,
		String repos_url,
		String events_url,
		String received_events_url,
		String type,
		boolean site_admin
	) {
	}

	public record Committer(
		String login,
		int id,
		String node_id,
		String avatar_url,
		String gravatar_id,
		String url,
		String html_url,
		String followers_url,
		String following_url,
		String gists_url,
		String starred_url,
		String subscriptions_url,
		String organizations_url,
		String repos_url,
		String events_url,
		String received_events_url,
		String type,
		boolean site_admin
	) {
	}

	public record Parent(
		String sha,
		String url,
		String html_url
	) {
	}
}
