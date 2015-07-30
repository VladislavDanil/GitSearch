package adapter;

/**
 * Object model content adapter.
 *
 * @author Danilov Vladislav
 */
public class RepositoriesElement {
    public String mStargazersCount;
    public String mAatarUrl;
    public String mNameRep;
    public String mLogin;
    public String mURL;

    public RepositoriesElement(String stargazers_count, String atar_url, String name_rep, String login, String mURL) {
        this.mStargazersCount = stargazers_count;
        this.mAatarUrl = atar_url;
        this.mNameRep = name_rep;
        this.mLogin = login;
        this.mURL=mURL;
    }
}
