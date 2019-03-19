package com.andaratech.youtobeapi.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.andaratech.youtobeapi.activity.DetailActivity;
import com.andaratech.youtobeapi.R;
import com.andaratech.youtobeapi.model.Movie;
import com.andaratech.youtobeapi.retrofit.Constant;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private List<Movie.Results> models;
    private Context context;

    public MovieAdapter(List<Movie.Results> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_movie,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder viewHolder, int i) {
        final Movie.Results model = models.get(i);
        viewHolder.txtTitle.setText(model.getTitle());
        viewHolder.txtRilis.setText(model.getRealease_date());

        Picasso.get()
                .load(Constant.BACKDROP_PATH + model.getBackdrop_path())
                .placeholder(R.drawable.ic_launcher_background)
                .fit().centerCrop()
                .into(viewHolder.imageView);

        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(Constant.INTENT_TITLE, model.getTitle());
                intent.putExtra(Constant.INTENT_BACKDROP, Constant.BACKDROP_PATH + model.getBackdrop_path());
                intent.putExtra(Constant.INTENT_DESCRIPTION, model.getOverview());
                context.startActivity(intent);
                Constant.MOVIE_ID = String.valueOf(model.getId());
                Constant.MOVIE_TITLE = model.getTitle();
            }
        });



    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView txtTitle, txtRilis;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtRilis = itemView.findViewById(R.id.txtRilis);
            imageView = itemView.findViewById(R.id.imgBackDrop);
        }
    }
}
