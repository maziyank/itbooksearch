package com.example.maziyank.itbooks.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.maziyank.itbooks.BookDetailActivity;
import com.example.maziyank.itbooks.R;
import com.example.maziyank.itbooks.model.BookDetailResponse;
import com.example.maziyank.itbooks.model.BookItem;
import com.example.maziyank.itbooks.service.API;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author: maziyank
 * Created on 17/02/18
 */

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {
    private Context Context;
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private List<BookItem> BookData;

    public BookAdapter(Context context, List<BookItem> BookData) {
        this.Context = context;
        this.BookData = BookData;

    }

    public void addItem(BookItem datum) {
        BookData.add(datum);
        notifyItemInserted(BookData.size());
    }

    public void addModeItem(List<BookItem> data) {
        BookData.addAll(data.size(), data);
        notifyItemInserted(BookData.size());
    }

    private String LimitStr(String arg){
        if (arg.length() > 24) {
            return arg.substring(0,20) + "...";
        } else {
            return arg;
        }
    }


    @Override
    public void onBindViewHolder(BookAdapter.ViewHolder holder, final int position) {
        holder.txtTitle.setText(LimitStr(BookData.get(position).getTitle()));
        Glide.with(Context)
                .load(BookData.get(position).getImage())
                .into(holder.imgView);

        View.OnClickListener onClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Context.getResources().getString(R.string.APIEndpoint))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                API restAPI = retrofit.create(API.class);
                String BookID = Long.toString(BookData.get(position).getID());
                Call<BookDetailResponse> getBookById = restAPI.getBookById(BookID);
                getBookById.enqueue(new Callback<BookDetailResponse>() {
                    @Override
                    public void onResponse(Call<BookDetailResponse> call, Response<BookDetailResponse> response) {
                        Log.d("tes", "onResponse: " + response.raw().toString());
                        if (response.body().getError().toString().equals("0")) {
                            Intent detailInfo = new Intent(Context, BookDetailActivity.class);
                            detailInfo.putExtra("ID", response.body().getID());
                            detailInfo.putExtra("Title", response.body().getTitle());
                            detailInfo.putExtra("Subtitle", response.body().getSubTitle());
                            detailInfo.putExtra("Description", response.body().getDescription());
                            detailInfo.putExtra("Author", response.body().getAuthor());
                            detailInfo.putExtra("Publisher", response.body().getPublisher());
                            detailInfo.putExtra("Year", response.body().getYear());
                            detailInfo.putExtra("ISBN", response.body().getISBN());
                            detailInfo.putExtra("Download", response.body().getDownload());
                            detailInfo.putExtra("Image", response.body().getImage());
                            detailInfo.putExtra("Page", response.body().getPage());
                            Context.startActivity(detailInfo);
                        } else {
                            Toast.makeText(Context, "Error Loading Data. Please Choose Another Book.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<BookDetailResponse> call, Throwable t) {
                        Toast.makeText(Context, "Error Loading Data", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        };

        holder.imgView.setOnClickListener(onClick);
        holder.itemView.setOnClickListener(onClick);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return BookData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle;
        ImageView imgView;

        public ViewHolder(View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.title);
            imgView = itemView.findViewById(R.id.thumbnail);
        }


    }

}


