package com.example.sodr.appstudio;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Sodré on 10/12/2017.
 */

public interface PhotoService {

    @GET("list_photos.php")
    Call<List<Photo>> getPhotos();

    @FormUrlEncoded
    @POST("insert_votes.php")
    Call<Vote> insertVote(@Field("name") String name, @Field("email") String email, @Field("photo_id") int photo_id);
}
