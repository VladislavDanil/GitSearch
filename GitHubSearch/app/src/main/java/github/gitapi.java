package github;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * The interface to query the server  github.
 *
 * @author Danilov Vladislav
 */
public interface Gitapi {
    /**
     * https://api.github.com/search/repositories?q=three.js+in:description+in:name&sort=stars&order=desc
     */
    @GET("/search/repositories?sort=stars&order=desc")
    /**метод, на входе получает строку и подставляет ее в запрос*/
    Example getFeed(@Query("q") String stringSearch);
}