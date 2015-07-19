package github;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface Gitapi {
    @GET("/search/repositories?sort=stars&order=desc")      //here is the other url part.best way is to start using /
    public void getFeed(@Query("q")String stringSearch,Callback<Gitmodel> response);     //string user is for passing values from edittext for eg: user=basil2style,google
}