package com.andaratech.youtobeapi.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.andaratech.youtobeapi.R;
import com.andaratech.youtobeapi.retrofit.Constant;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    TextView txtTitle, txtDetail, txtMore;
    ImageView imageView;
    Button button;
    Bundle bundle;

    private  int line_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        bundle = getIntent().getExtras();

        txtDetail = findViewById(R.id.txtDesc);
        txtMore = findViewById(R.id.txtMore);
        txtTitle = findViewById(R.id.txtTitle);
        imageView = findViewById(R.id.imgView);
        button = findViewById(R.id.button);

        Picasso.get().load(bundle.getString(Constant.INTENT_BACKDROP))
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(imageView);

        txtTitle.setText(bundle.getString(Constant.INTENT_TITLE));
        txtDetail.setText(bundle.getString(Constant.INTENT_DESCRIPTION));

        txtDetail.post(new Runnable() {
            @Override
            public void run() {
                line_count = txtDetail.getLineCount();
            }
        });

        txtMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtDetail.getMaxLines() <= 2){
                    txtMore.setText("Less");
                    txtDetail.setMaxLines(line_count);
                }else{
                    txtMore.setText("...More");
                    txtDetail.setMaxLines(2);
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, TrailerActivity.class);
                startActivity(intent);
            }
        });

        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
