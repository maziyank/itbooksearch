package com.example.maziyank.itbooks.service;

import com.example.maziyank.itbooks.model.BookDetailResponse;
import com.example.maziyank.itbooks.model.ResponseBook;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by maziyank on 17/02/18.
 */

public interface API {
    @GET("search/{query}/page/{number}")
    Call<ResponseBook> getBooksData(@Path("query") String query, @Path("number") String page);

    @GET("book/{query}")
    Call<BookDetailResponse> getBookById(@Path("query") String query);
}