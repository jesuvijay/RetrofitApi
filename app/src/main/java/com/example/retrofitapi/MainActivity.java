package com.example.retrofitapi;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView tvResult;
    private JsonPlaceHolder jsonPlaceHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvResult = findViewById(R.id.tv_result);
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request originalRequest=chain.request();
                        Request newReq=originalRequest.newBuilder()
                                .addHeader("Intercepto", "xyrz").build();
                        return chain.proceed(newReq);
                    }
                })
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);
//         getPosts();
//        getComments();

//        createPosts();

        updatePost();

//        deletePost();


    }

    private void deletePost() {
        Call<Void> voidCall = jsonPlaceHolder.deletePost(5);
        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                tvResult.setText("Code: " + response.code());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                tvResult.setText(t.getMessage());
            }
        });
    }

    private void updatePost() {
        Post post = new Post(12, null, "new Text");
        Map<String,String> stringStringMap=new HashMap<>();
        stringStringMap.put("Map-header1", "123");
        stringStringMap.put("Map-header2", "123");
        stringStringMap.put("Map-header3", "123");
        Call<Post> postCall = jsonPlaceHolder.patchPost(stringStringMap,5, post);
        postCall.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {
                    tvResult.setText("code" + response.code());
                    return;
                }
                Post post1 = response.body();
                tvResult.setText(response.code() + "\n\n");
                tvResult.setText(post1.toString());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                tvResult.setText(t.getMessage());
            }
        });
    }

    private void createPosts() {
//        Post post = new Post(10, "jesu", "welcome");
        Call<Post> postCall = jsonPlaceHolder.createPostUrls(23, "jeus", "welcome");
        postCall.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {
                    tvResult.setText("code" + response.code());
                    return;
                }
                Post post1 = response.body();
                tvResult.setText(response.code() + "\n\n");
                tvResult.setText(post1.toString());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                tvResult.setText(t.getMessage());
            }
        });
    }

    private void getComments() {
        Call<List<Comment>> listCall = jsonPlaceHolder.gettComments("/posts/2/comments");
        listCall.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {

                if (!response.isSuccessful()) {
                    tvResult.setText("code" + response.code());
                    return;
                }
                List<Comment> comments = response.body();
                if (comments != null) {
                    for (Comment post : comments) {
                        tvResult.append(post.toString());

                    }
                } else tvResult.append("No data");
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                tvResult.setText(t.getMessage());
            }
        });
    }

    private void getPosts() {
        Map<String, String> stringStringMap = new HashMap<>();
        stringStringMap.put("userId", "1");
        stringStringMap.put("_sort", "id");
        stringStringMap.put("_order", "desc");

        Call<List<Post>> hgetPosts = jsonPlaceHolder.getPosts(2, "id", "desc");
        hgetPosts.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    tvResult.setText("code" + response.code());
                    return;
                }
                List<Post> posts = response.body();
                if (posts != null) {
                    for (Post post : posts) {
                        tvResult.append(post.toString());

                    }
                } else tvResult.append("No data");
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                tvResult.setText(t.getMessage());
            }
        });
    }
}
