package com.example.helloworld;


public class MainItem {
	private int imagView;
	private String tvName;
	
	public MainItem(int imagView, String tvName) {
		this.imagView = imagView;
		this.tvName = tvName;
	}

	public int getImagView() {
		return imagView;
	}

	public void setImagView(int imagView) {
		this.imagView = imagView;
	}

	public String getTvName() {
		return tvName;
	}

	public void setTvName(String tvName) {
		this.tvName = tvName;
	}
	

}
