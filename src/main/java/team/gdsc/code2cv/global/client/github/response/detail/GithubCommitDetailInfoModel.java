package team.gdsc.code2cv.global.client.github.response.detail;
public record GithubCommitDetailInfoModel(
	String sha,
	String node_id,
	GithubCommitInfoModel.Commit commit,
	String url,
	String html_url,
	String comments_url,
	GithubCommitInfoModel.Author author,
	GithubCommitInfoModel.Commit.Committer committer,
	GithubCommitInfoModel.Parent[] parents,
	State state,
	Files[] files
) {
	public record State(
		String state,
		String title,
		String body,
		String created_at,
		String updated_at,
		String closed_at,
		String merged_at,
		String merge_commit_sha,
		GithubCommitInfoModel.Author assignee,
		GithubCommitInfoModel.Author[] assignees,
		GithubCommitInfoModel.Author[] requested_reviewers,
		GithubCommitInfoModel.Author[] requested_teams,
		GithubCommitInfoModel.Author[] labels,
		boolean draft,
		boolean merged,
		boolean mergeable,
		boolean rebaseable,
		boolean mergeable_state,
		String merged_by,
		int comments,
		int review_comments,
		boolean maintainer_can_modify,
		boolean commits,
		boolean additions,
		boolean deletions,
		boolean changed_files
	) {
	}

	public record Files(
		String sha,
		String filename,
		String status,
		int additions,
		int deletions,
		int changes,
		String blob_url,
		String raw_url,
		String contents_url,
		String patch
	) {
	}
}
