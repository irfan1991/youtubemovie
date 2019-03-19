package com.andaratech.youtobeapi.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.andaratech.youtobeapi.R;
import com.andaratech.youtobeapi.activity.DetailActivity;
import com.andaratech.youtobeapi.activity.TrailerActivity;
import com.andaratech.youtobeapi.model.Movie;
import com.andaratech.youtobeapi.model.Video;
import com.andaratech.youtobeapi.retrofit.Constant;
import com.squareup.picasso.Picasso;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {
    private List<Video.Results> models;
    private Context context;

    public VideoAdapter(List<Video.Results> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @NonNull
    @Override
    public VideoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_video,viewGroup,false);

        return new VideoAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoAdapter.ViewHolder viewHolder, int i) {
        final Video.Results model = models.get(i);
        viewHolder.txtView.setText(model.getName());
        viewHolder.txtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TrailerActivity.youTubePlayer.loadVideo(model.getKey());
            }
        });

        TrailerActivity.youTubePlayer.cueVideo(models.get(0).getKey());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView txtView;

        RelativeLayout relativeLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtView = itemView.findViewById(R.id.textView);

        }
    }
}

