package com.example.maziyank.itbooks.model;

public class BookItem {
	private String Description;
	private String isbn;
	private String Title;
	private long ID;
	private String Image;
	private String SubTitle;

	public void setDescription(String description){
		this.Description = description;
	}

	public String getDescription(){
		return Description;
	}

	public void setIsbn(String isbn){
		this.isbn = isbn;
	}

	public String getIsbn(){
		return isbn;
	}

	public void setTitle(String title){
		this.Title = title;
	}

	public String getTitle(){
		return Title;
	}

	public void setID(long iD){
		this.ID = iD;
	}

	public long getID(){
		return ID;
	}

	public void setImage(String image){
		this.Image = image;
	}

	public String getImage(){
		return Image;
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
			"BookItem{" +
			"Description = '" + Description + '\'' +
			",isbn = '" + isbn + '\'' +
			",Title = '" + Title + '\'' +
			",ID = '" + ID + '\'' +
			",Image = '" + Image + '\'' +
			",SubTitle = '" + SubTitle + '\'' +
			"}";
		}
}
