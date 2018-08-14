package com.nibble.wheelofjeopardy.questionBank;

import java.net.URI;

public class Question {
	private int id;
	private String question;
	private String answer;
	/**
	 private URI image;
	 private URI sound;
	 private URI video;
	 **/

	public Question(int id, String question, String answer){//}, URI image, URI sound, URI video){
		this.id = id;
		this.question = question;
		this.answer = answer;
		//this.image = image;
		//this.sound = sound;
		//this.video = video;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	/**
	 public URI getImage() {
	 return image;
	 }

	 public void setImage(URI image) {
	 this.image = image;
	 }

	 public URI getSound() {
	 return sound;
	 }

	 public void setSound(URI sound) {
	 this.sound = sound;
	 }

	 public URI getVideo() {
	 return video;
	 }

	 public void setVideo(URI video) {
	 this.video = video;
	 }
	 **/
}