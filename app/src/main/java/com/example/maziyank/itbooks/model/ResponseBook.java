package com.example.maziyank.itbooks.model;

import java.util.List;

public class ResponseBook{
	private String Total;
	private String Error;
	private double Time;
	private int Page;
	private List<BookItem> Books;

	public void setTotal(String total){
		this.Total = total;
	}

	public String getTotal(){
		return Total;
	}

	public void setError(String error){
		this.Error = error;
	}

	public String getError(){
		return Error;
	}

	public void setTime(double time){
		this.Time = time;
	}

	public double getTime(){
		return Time;
	}

	public void setPage(int page){
		this.Page = page;
	}

	public int getPage(){
		return Page;
	}

	public void setBooks(List<BookItem> books){
		this.Books = books;
	}

	public List<BookItem> getBooks(){
		return Books;
	}

	@Override
 	public String toString(){
		return 
			"ResponseBook{" + 
			"Total = '" + Total + '\'' +
			",Error = '" + Error + '\'' +
			",Time = '" + Time + '\'' +
			",Page = '" + Page + '\'' +
			",Books = '" + Books + '\'' +
			"}";
		}
}