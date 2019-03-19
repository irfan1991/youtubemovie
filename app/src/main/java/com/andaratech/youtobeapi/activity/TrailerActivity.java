package com.andaratech.youtobeapi.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.andaratech.youtobeapi.R;
import com.andaratech.youtobeapi.adapter.VideoAdapter;
import com.andaratech.youtobeapi.model.Movie;
import com.andaratech.youtobeapi.model.Video;
import com.andaratech.youtobeapi.retrofit.API;
import com.andaratech.youtobeapi.retrofit.APIInterface;
import com.andaratech.youtobeapi.retrofit.Constant;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrailerActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProgressBar progressBar;
    public static YouTubePlayer youTubePlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trailer);

        recyclerView = findViewById(R.id.recycleView);
        progressBar = findViewById(R.id.progresBar);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        getVideo();

        YouTubePlayerFragment youtubeFragment = (YouTubePlayerFragment)
                getFragmentManager().findFragmentById(R.id.youtubeFragment);
        youtubeFragment.initialize( Constant.YOUTOBE_API_KEY,
                new YouTubePlayer.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                        YouTubePlayer player, boolean b) {
                        youTubePlayer = player;


                    }
                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                        YouTubeInitializationResult youTubeInitializationResult) {

                    }
                });
        getSupportActionBar().setTitle(Constant.MOVIE_TITLE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    private void  getVideo(){
        APIInterface apiInterface = API.getUrl().create(APIInterface.class);
        Call<Video> call = apiInterface.getVideo(Constant.MOVIE_ID,Constant.KEY);

        call.enqueue(new Callback<Video>() {
            @Override
            public void onResponse(Call<Video> call, Response<Video> response) {

                Video video = response.body();
                List<Video.Results> results = video.getResults();
                recyclerView.setAdapter(new VideoAdapter(results, TrailerActivity.this));
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<Video> call, Throwable t) {

            }
        });
    }
}
