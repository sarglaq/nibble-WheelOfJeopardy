package com.nibble.wheelofjeopardy.questionBank;

import java.util.Vector;

public class QuestionGroup {
	private int id; 
	private Vector<Question> questions;

	public QuestionGroup(){
		questions = new Vector<>();
	}
	
	public QuestionGroup(Vector<Question> questions){
	    this.questions = questions;
    }
	
	public Vector<Question> getAllQuestions(){
	    return questions;
    }
	
	public void addQuesiton(Question question){
	    questions.add(question);
    }
	
	public void removeQuestion(Question question){
	    questions.removeElement(question);
    }
	
	public Question getQuestion(){
	    // todo
        return null;
    }
	
	public Question getQuestion(int ID){
	    // todo
        return null;
    }
	
}