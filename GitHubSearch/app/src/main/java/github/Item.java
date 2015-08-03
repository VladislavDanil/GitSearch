package github;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Object model query result.
 * @author Danilov Vladislav
 */
@DatabaseTable(tableName = "Item")
public class Item {
    @DatabaseField(generatedId = true)
    private int idbase;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Example example;
    @Expose
    @DatabaseField
    public Integer id;
    @Expose
    @DatabaseField
    public String name;
    @SerializedName("full_name")
    @Expose
    @DatabaseField
    public String fullName;
    @Expose
    @DatabaseField(foreign = true)
    public Owner owner;
    @SerializedName("private")
    @Expose
    @DatabaseField
    public Boolean _private;
    @SerializedName("html_url")
    @Expose
    @DatabaseField
    public String htmlUrl;
    @Expose
    @DatabaseField
    public String description;
    @Expose
    @DatabaseField
    public Boolean fork;
    @Expose
    @DatabaseField
    public String url;
    @SerializedName("forks_url")
    @Expose
    @DatabaseField
    public String forksUrl;
    @SerializedName("keys_url")
    @Expose
    @DatabaseField
    public String keysUrl;
    @SerializedName("collaborators_url")
    @Expose
    @DatabaseField
    public String collaboratorsUrl;
    @SerializedName("teams_url")
    @Expose
    @DatabaseField
    public String teamsUrl;
    @SerializedName("hooks_url")
    @Expose
    @DatabaseField
    public String hooksUrl;
    @SerializedName("issue_events_url")
    @Expose
    @DatabaseField
    public String issueEventsUrl;
    @SerializedName("events_url")
    @Expose
    @DatabaseField
    public String eventsUrl;
    @SerializedName("assignees_url")
    @Expose
    @DatabaseField
    public String assigneesUrl;
    @SerializedName("branches_url")
    @Expose
    @DatabaseField
    public String branchesUrl;
    @SerializedName("tags_url")
    @Expose
    @DatabaseField
    public String tagsUrl;
    @SerializedName("blobs_url")
    @Expose
    @DatabaseField
    public String blobsUrl;
    @SerializedName("git_tags_url")
    @Expose
    @DatabaseField
    public String gitTagsUrl;
    @SerializedName("git_refs_url")
    @Expose
    @DatabaseField
    public String gitRefsUrl;
    @SerializedName("trees_url")
    @Expose
    @DatabaseField
    public String treesUrl;
    @SerializedName("statuses_url")
    @Expose
    @DatabaseField
    public String statusesUrl;
    @SerializedName("languages_url")
    @Expose
    @DatabaseField
    public String languagesUrl;
    @SerializedName("stargazers_url")
    @Expose
    @DatabaseField
    public String stargazersUrl;
    @SerializedName("contributors_url")
    @Expose
    @DatabaseField
    public String contributorsUrl;
    @SerializedName("subscribers_url")
    @Expose
    @DatabaseField
    public String subscribersUrl;
    @SerializedName("subscription_url")
    @Expose
    @DatabaseField
    public String subscriptionUrl;
    @SerializedName("commits_url")
    @Expose
    @DatabaseField
    public String commitsUrl;
    @SerializedName("git_commits_url")
    @Expose
    @DatabaseField
    public String gitCommitsUrl;
    @SerializedName("comments_url")
    @Expose
    @DatabaseField
    public String commentsUrl;
    @SerializedName("issue_comment_url")
    @Expose
    @DatabaseField
    public String issueCommentUrl;
    @SerializedName("contents_url")
    @Expose
    @DatabaseField
    public String contentsUrl;
    @SerializedName("compare_url")
    @Expose
    @DatabaseField
    public String compareUrl;
    @SerializedName("merges_url")
    @Expose
    @DatabaseField
    public String mergesUrl;
    @SerializedName("archive_url")
    @Expose
    @DatabaseField
    public String archiveUrl;
    @SerializedName("downloads_url")
    @Expose
    @DatabaseField
    public String downloadsUrl;
    @SerializedName("issues_url")
    @Expose
    @DatabaseField
    public String issuesUrl;
    @SerializedName("pulls_url")
    @Expose
    @DatabaseField
    public String pullsUrl;
    @SerializedName("milestones_url")
    @Expose
    @DatabaseField
    public String milestonesUrl;
    @SerializedName("notifications_url")
    @Expose
    @DatabaseField
    public String notificationsUrl;
    @SerializedName("labels_url")
    @Expose
    @DatabaseField
    public String labelsUrl;
    @SerializedName("releases_url")
    @Expose
    @DatabaseField
    public String releasesUrl;
    @SerializedName("created_at")
    @Expose
    @DatabaseField
    public String createdAt;
    @SerializedName("updated_at")
    @Expose
    @DatabaseField
    public String updatedAt;
    @SerializedName("pushed_at")
    @Expose
    @DatabaseField
    public String pushedAt;
    @SerializedName("git_url")
    @Expose
    @DatabaseField
    public String gitUrl;
    @SerializedName("ssh_url")
    @Expose
    @DatabaseField
    public String sshUrl;
    @SerializedName("clone_url")
    @Expose
    @DatabaseField
    public String cloneUrl;
    @SerializedName("svn_url")
    @Expose
    @DatabaseField
    public String svnUrl;
    @Expose
    @DatabaseField
    public Object homepage;
    @Expose
    @DatabaseField
    public Integer size;
    @SerializedName("stargazers_count")
    @Expose
    @DatabaseField
    public Integer stargazersCount;
    @SerializedName("watchers_count")
    @Expose
    @DatabaseField
    public Integer watchersCount;
    @Expose
    @DatabaseField
    public Object language;
    @SerializedName("has_issues")
    @Expose
    @DatabaseField
    public Boolean hasIssues;
    @SerializedName("has_downloads")
    @Expose
    @DatabaseField
    public Boolean hasDownloads;
    @SerializedName("has_wiki")
    @Expose
    @DatabaseField
    public Boolean hasWiki;
    @SerializedName("has_pages")
    @Expose
    @DatabaseField
    public Boolean hasPages;
    @SerializedName("forks_count")
    @Expose
    @DatabaseField
    public Integer forksCount;
    @SerializedName("mirror_url")
    @Expose
    @DatabaseField
    public Object mirrorUrl;
    @SerializedName("open_issues_count")
    @Expose
    @DatabaseField
    public Integer openIssuesCount;
    @Expose
    @DatabaseField
    public Integer forks;
    @SerializedName("open_issues")
    @Expose
    @DatabaseField
    public Integer openIssues;
    @Expose
    @DatabaseField
    public Integer watchers;
    @SerializedName("default_branch")
    @Expose
    @DatabaseField
    public String defaultBranch;
    @Expose
    @DatabaseField
    public Double score;
}
