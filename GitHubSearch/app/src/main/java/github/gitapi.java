package github;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Path;

public interface Gitapi {
    @GET("/users/{user}")      //here is the other url part.best way is to start using /
    public Gitmodel getFeed(@Path("user") String user);     //string user is for passing values from edittext for eg: user=basil2style,google
    //response is the response from the server which is now in the POJO
}