package com.example.maziyank.itbooks;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookDetailActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.bookCover)
    ImageView bookCover;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.txtTitle)
    TextView txtTitle;
    @BindView(R.id.txtSubTitle)
    TextView txtSubTitle;
    @BindView(R.id.txtDescription)
    TextView txtDescription;
    @BindView(R.id.txtAuthor)
    TextView txtAuthor;
    @BindView(R.id.txtISBN)
    TextView txtISBN;
    @BindView(R.id.txtYear)
    TextView txtYear;
    @BindView(R.id.txtPages)
    TextView txtPages;
    @BindView(R.id.txtPublisher)
    TextView txtPublisher;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.fab2)
    FloatingActionButton fab2;

    private String downloadURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(downloadURL));

                startActivity(i);
            }
        });

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("http://isbn.directory/book/"+txtISBN.getText().toString()));
                startActivity(i);
            }
        });


        Intent intent = getIntent();
        txtTitle.setText(intent.getStringExtra("Title"));
        txtSubTitle.setText(intent.getStringExtra("Subtitle"));
        txtDescription.setText(intent.getStringExtra("Description"));
        txtAuthor.setText(intent.getStringExtra("Author"));
        txtPublisher.setText(intent.getStringExtra("Publisher"));
        txtYear.setText(intent.getStringExtra("Year"));
        txtISBN.setText(intent.getStringExtra("ISBN"));
        txtPages.setText(intent.getStringExtra("Page"));
        downloadURL = intent.getStringExtra("Download");

        Glide.with(this)
                .load(intent.getStringExtra("Image"))
                .into(bookCover);

        final int semiTransparentGrey = Color.argb(100, 79, 11, 71);
        bookCover.setColorFilter(semiTransparentGrey, PorterDuff.Mode.SRC_ATOP);
    }
}
