package com.hekai.haosuanfa.shared;

import java.io.Serializable;

public class Record implements Serializable {

	private static final long serialVersionUID = -2397966618346518219L;
	
	private Long id;
	private String name;
	private String title;
	private int[] scores;

	public Record(String name, String title, int[] scores) {
		super();
		this.name = name;
		this.title = title;
		this.scores = scores;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int[] getScores() {
		return scores;
	}

	public void setScores(int[] scores) {
		this.scores = scores;
	}

}
