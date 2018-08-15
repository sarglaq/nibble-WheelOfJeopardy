package com.nibble.wheelofjeopardy.questionBank;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

import org.json.simple.parser.ParseException;

public class QuestionGroup {
	private int id;
	public String type;
	private Vector<Question> questions = new Vector<Question>();
	private JSONHelper jh;

	public QuestionGroup(int id, String fileDirPath) throws IOException, ParseException
	{
		this.id = id;
		jh = new JSONHelper(fileDirPath);
		jh.getID(this.id);
		type = jh.getType();
		for(int i = 0; i < 5; i += 1)
		{
			Question q = new Question(i+1, jh.readQuestion(i+1), jh.readAnswer(i+1));
			questions.add(q);
		}
		//questions = new Vector<>();
	}

	public QuestionGroup(
			int id,
			String type,
			String[] q1,
			String[] q2,
			String[] q3,
			String[] q4,
			String[] q5,
			String fileDirPath) throws FileNotFoundException, IOException, ParseException
	{
		jh = new JSONHelper(fileDirPath);
		jh.writeQuestionGroup(id, type, q1, q2, q3, q4, q5);
		type = jh.getType();
		this.id = id;
		for(int i = 0; i < 5; i += 1)
		{
			Question q = new Question(i+1, jh.readQuestion(i+1), jh.readAnswer(i+1));
			questions.add(q);
		}
	}

	/**
	 public QuestionGroup(Vector<Question> questions){
	 this.questions = questions;
	 }
	 **/

	public Vector<Question> getAllQuestions(){
		return questions;
	}

	/**
	 public void addQuesiton(Question question){
	 questions.add(question);
	 }
	 **/

	/** not sure if we need it
	 public void removeQuestion(Question question){
	 int id = question.ge
	 questions.removeElement(question);
	 }
	 **/
	/**
	 public Question getQuestion(){
	 // todo
	 return null;
	 }
	 **/

	public Question getQuestion(int ID){
		return questions.get(ID-1);
	}

}