package github;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Интерфейс для запроса к серверу github
 *
 * @author Данилов Владислав
 */
public interface Gitapi {
    /**
     * get запрос к серверу github
     * Пример:https://api.github.com/search/repositories?q=three.js+in:description+in:name&sort=stars&order=desc
     */
    @GET("/search/repositories?sort=stars&order=desc")
    /**метод, на входе получает строку и подставляет ее в запрос*/
    Example getFeed(@Query("q") String stringSearch);
}