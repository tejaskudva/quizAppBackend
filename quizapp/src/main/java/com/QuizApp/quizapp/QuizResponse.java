package com.QuizApp.quizapp;

public class QuizResponse {
	
	private Integer id;
	private String response;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	
	@Override
	public String toString() {
		return "QuizResponse [id=" + id + ", response=" + response + "]";
	}
}
