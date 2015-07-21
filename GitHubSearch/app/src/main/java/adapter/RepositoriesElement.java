package adapter;

/**
 * Created by Nitrogenium on 21.07.15.
 */
public class RepositoriesElement {
    public int stargazers_count;
    public int avatar_url;
    public String name_rep;
    public String login;

    public RepositoriesElement(int stargazers_count, int avatar_url, String name_rep, String login) {
        this.stargazers_count = stargazers_count;
        this.avatar_url = avatar_url;
        this.name_rep = name_rep;
        this.login = login;
    }
}
