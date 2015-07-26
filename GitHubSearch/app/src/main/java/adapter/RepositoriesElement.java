package adapter;
/**
 * Класс реализует адаптер для result Layout.
 *
 * @author Данилов Владислав
 */
public class RepositoriesElement {
    public String stargazers_count;
    public String avatar_url;
    public String name_rep;
    public String login;

    public RepositoriesElement(String stargazers_count, String avatar_url, String name_rep, String login) {
        this.stargazers_count = stargazers_count;
        this.avatar_url = avatar_url;
        this.name_rep = name_rep;
        this.login = login;
    }
}
