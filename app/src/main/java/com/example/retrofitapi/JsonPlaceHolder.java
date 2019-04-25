package com.example.retrofitapi;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface JsonPlaceHolder {
    @GET("posts")
    Call<List<Post>> getPosts(@Query("userId")int userId,@Query("_sort")String sort,@Query("_order")String order);

    @GET("posts")
    Call<List<Post>> getPosts(@QueryMap Map<String,String> stringStringMap);
    @GET("posts/{id}/comments")
    Call<List<Comment>>  gettComments(@Path("id") int postid);

    @GET
    Call<List<Comment>> gettComments(@Url String url);

    @POST("posts")
    Call<Post> createPost(@Body Post post);

    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPostUrls(@Field("userId")int userId,
                              @Field("title")String title,
                              @Field("body")String body);


    @Headers({"Static-Header:123","Static-Header:123",})
    @PUT("posts/{id}")
    Call<Post> putPost(@Header ("Dynamic-header") String mdarta,@Path("id") int id, @Body Post post);
    @PATCH("posts/{id}")

    Call<Post> patchPost(@HeaderMap Map<String,String> stringStringMap, @Path("id") int id, @Body Post post);
    @DELETE("posts/{id}")
    Call<Void> deletePost(@Path("id") int id);
}
