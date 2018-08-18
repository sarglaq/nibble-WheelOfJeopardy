package com.nibble.wheelofjeopardy.questionBank;

import com.nibble.wheelofjeopardy.questionBoard.Category;

import java.io.IOException;
import java.util.Random;
//import java.net.URL;
import java.util.Vector;

import org.json.simple.parser.ParseException;

public class QuestionBank {
	//private String filePath = "/storage/self/primary/QuestionBank/";
    private String filePath = "/data/user/0/com.nibble.wheelofjeopardy/files/";
	private Vector<QuestionGroup> groups;
	private Vector<String> typePool;

	public QuestionBank(){
		groups = new Vector<QuestionGroup>();
		typePool = new Vector<String>();
	}

	private void initializeQB() throws IOException, ParseException
    {
        System.out.println("Initializing the question bank");
	    if (!addGroup(DefaultQuestions.getDefaultCategory1())) {
            System.out.println("Failed to add a question group for category 1.");
        }
        if (!addGroup(DefaultQuestions.getDefaultCategory2())) {
            System.out.println("Failed to add a question group for category 2.");
        }
        if (!addGroup(DefaultQuestions.getDefaultCategory3())) {
            System.out.println("Failed to add a question group for category 3.");
        }
        if (!addGroup(DefaultQuestions.getDefaultCategory4())) {
            System.out.println("Failed to add a question group for category 4.");
        }
        if (!addGroup(DefaultQuestions.getDefaultCategory5())) {
            System.out.println("Failed to add a question group for category 5.");
        }
        if (!addGroup(DefaultQuestions.getDefaultCategory6())) {
            System.out.println("Failed to add a question group for category 6.");
        }
    }

	public void open() throws IOException, ParseException
	{
	    JSONHelper jh = new JSONHelper(1);
	    jh.getID(1);
	    try {
	        jh.readQuestion(1);
	        System.out.println("question bank alraedy initialzied");
	        // if this succeeds then the bank has already been initialized
        } catch (Exception e) {
	        initializeQB();
        }

		for (Category category : Category.values()) {
			addGroup(new QuestionGroup(category.getValue()));
        }
	}

	public void close(){
		typePool.removeAllElements();
		groups.removeAllElements();
	}

	public boolean addGroup(QuestionGroup questionGroup){
		if(typePool.contains(questionGroup.type))
		{
            System.out.println("Already have a group with type: " + questionGroup.type);
			return false;//should deny this route as one game board should only allow 6 different kinds of question types
		}
		else
		{
			groups.add(questionGroup);
			typePool.add(questionGroup.type);
			return true;
		}
	}

	/**
	 public void removeGroup(QuestionGroup questionGroup){
	 // todo
	 groups.removeElement(questionGroup));
	 }
	 **/

	public QuestionGroup getGroup(int groupID){
		return groups.get(groupID-1);
	}

	public Vector<QuestionGroup> getAllGroups(){
		return groups;
	}

}