package com.andaratech.youtobeapi.retrofit;

import com.andaratech.youtobeapi.model.Movie;
import com.andaratech.youtobeapi.model.Video;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {
    @GET("movie/{category}")
    Call<Movie> getMovie(

    @Path("category") String movie_category,
    @Query("api_key") String key,
    @Query("language") String language,
    @Query("page") String page
    );

    @GET("movie/{id}/videos")
    Call<Video> getVideo(
      @Path("id") String movie_id,
      @Query("api_key") String key
    );

}
