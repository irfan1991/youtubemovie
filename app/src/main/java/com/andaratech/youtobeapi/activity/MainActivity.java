package com.andaratech.youtobeapi.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.andaratech.youtobeapi.R;
import com.andaratech.youtobeapi.adapter.MovieAdapter;
import com.andaratech.youtobeapi.model.Movie;
import com.andaratech.youtobeapi.retrofit.API;
import com.andaratech.youtobeapi.retrofit.APIInterface;
import com.andaratech.youtobeapi.retrofit.Constant;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycleView);
        progressBar = findViewById(R.id.progresBar);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        getMovie("popular");
    }

    private void getMovie(String category){
        APIInterface apiInterface = API.getUrl().create(APIInterface.class);
        String language;
        Call<Movie> call = apiInterface.getMovie(category,Constant.KEY ,"en-US" ,"1");
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                Movie movie = response.body();
                List<Movie.Results> results = movie.getResults();
                recyclerView.setAdapter(new MovieAdapter(results, MainActivity.this));
                progressBar.setVisibility(View.GONE);
                Log.e("test","Data ada ");
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id  == R.id.action_playing){
            getSupportActionBar().setTitle("Movie Now Playing");
            getMovie("now_playing");
            return  true;

        }else if (id == R.id.action_populer){
            getSupportActionBar().setTitle("Movie Populer");
            getMovie("popular");
            return  true;
        }
        return super.onOptionsItemSelected(item);
    }
}
