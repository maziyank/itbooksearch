package com.example.maziyank.itbooks;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maziyank.itbooks.adapter.BookAdapter;
import com.example.maziyank.itbooks.custom.EndlessRecyclerViewScrollListener;
import com.example.maziyank.itbooks.model.BookItem;
import com.example.maziyank.itbooks.model.ResponseBook;
import com.example.maziyank.itbooks.service.API;
import com.rey.material.widget.ProgressView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.progressView)
    ProgressView progressView;
    @BindView(R.id.txtInfo)
    TextView txtInfo;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private int MaxPage;
    private int CurrentPage;
    public List<BookItem> bookList;
    public BookAdapter mAdapter;
    private EndlessRecyclerViewScrollListener scrollListener;
    private Parcelable recyclerViewState;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the options menu from XML
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id=item.getItemId();
        if (id==R.id.infoMenu){
            Log.d("tes", "onOptionsItemSelected: Tes");
            startActivity(new Intent(MainActivity.this, AboutActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        GetQuery(intent);
    }

    private String GetQuery(Intent intent) {
        String query;

        query = null;

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            query = intent.getStringExtra(SearchManager.QUERY);
        }

        return query;
    }

    private void LoadData(final String query) {
        recyclerViewState = recyclerView.getLayoutManager().onSaveInstanceState();
        if (this.CurrentPage==1) progressView.setVisibility(View.VISIBLE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://it-ebooks-api.info/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API restAPI = retrofit.create(API.class);


        Call<ResponseBook> getBooksData = restAPI.getBooksData(query, Integer.toString(this.CurrentPage));
        getBooksData.enqueue(new Callback<ResponseBook>() {
            @Override
            public void onResponse(Call<ResponseBook> call, Response<ResponseBook> response) {
                Log.d("API", "onResponse: "+ response.raw().toString());
                if (response.body().getPage() == 1)
                    bookList = response.body().getBooks();
                else {
                    List<BookItem> bl = response.body().getBooks();
                    bookList.addAll(bl.size(), bl);
                    Log.d("ss", Integer.toString(bookList.size()));
                    recyclerView.getLayoutManager().onRestoreInstanceState(recyclerViewState);

                }

                mAdapter = new BookAdapter(MainActivity.this, bookList);
                if (Integer.parseInt(response.body().getTotal())>0)  {
                    recyclerView.setAdapter(mAdapter);
                    txtInfo.setVisibility(View.INVISIBLE);
                    recyclerView.setVisibility(View.VISIBLE);

                } else {
                    txtInfo.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.INVISIBLE);
                    txtInfo.setText("No Books Found");
                }
                progressView.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onFailure(Call<ResponseBook> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Connection Fail" + t.toString(), Toast.LENGTH_SHORT).show();
                txtInfo.setText("No Books Found");
                txtInfo.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.INVISIBLE);
                progressView.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        GridLayoutManager manager = (GridLayoutManager) recyclerView.getLayoutManager();

        scrollListener = new EndlessRecyclerViewScrollListener(manager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                Log.d("loadmore", "onLoadMore: ");
                MainActivity.this.CurrentPage += 1;
                LoadData("HTML");

            }
        };
        // Adds the scroll listener to RecyclerView
        recyclerView.addOnScrollListener(scrollListener);

        String query = (GetQuery(getIntent()) != null) ? GetQuery(getIntent()) : "HTML";
        this.CurrentPage = 1;
        LoadData(query);
    }


}
