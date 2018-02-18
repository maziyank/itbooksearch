package com.example.maziyank.itbooks.model;

public class BookDetailResponse{
	private String Description;
	private String Error;
	private String Title;
	private double Time;
	private String Page;
	private String Publisher;
	private String Image;
	private String ISBN;
	private String Year;
	private String Author;
	private long ID;
	private String Download;
	private String SubTitle;

	public void setDescription(String description){
		this.Description = description;
	}

	public String getDescription(){
		return Description;
	}

	public void setError(String error){
		this.Error = error;
	}

	public String getError(){
		return Error;
	}

	public void setTitle(String title){
		this.Title = title;
	}

	public String getTitle(){
		return Title;
	}

	public void setTime(double time){
		this.Time = time;
	}

	public double getTime(){
		return Time;
	}

	public void setPage(String page){
		this.Page = page;
	}

	public String getPage(){
		return Page;
	}

	public void setPublisher(String publisher){
		this.Publisher = publisher;
	}

	public String getPublisher(){
		return Publisher;
	}

	public void setImage(String image){
		this.Image = image;
	}

	public String getImage(){
		return Image;
	}

	public void setISBN(String iSBN){
		this.ISBN = iSBN;
	}

	public String getISBN(){
		return ISBN;
	}

	public void setYear(String year){
		this.Year = year;
	}

	public String getYear(){
		return Year;
	}

	public void setAuthor(String author){
		this.Author = author;
	}

	public String getAuthor(){
		return Author;
	}

	public void setID(long iD){
		this.ID = iD;
	}

	public long getID(){
		return ID;
	}

	public void setDownload(String download){
		this.Download = download;
	}

	public String getDownload(){
		return Download;
	}

	public void setSubTitle(String subTitle){
		this.SubTitle = subTitle;
	}

	public String getSubTitle(){
		return SubTitle;
	}

	@Override
 	public String toString(){
		return 
			"BookDetailResponse{" + 
			"Description = '" + Description + '\'' +
			",Error = '" + Error + '\'' +
			",Title = '" + Title + '\'' +
			",Time = '" + Time + '\'' +
			",Page = '" + Page + '\'' +
			",Publisher = '" + Publisher + '\'' +
			",Image = '" + Image + '\'' +
			",ISBN = '" + ISBN + '\'' +
			",Year = '" + Year + '\'' +
			",Author = '" + Author + '\'' +
			",ID = '" + ID + '\'' +
			",Download = '" + Download + '\'' +
			",SubTitle = '" + SubTitle + '\'' +
			"}";
		}
}
