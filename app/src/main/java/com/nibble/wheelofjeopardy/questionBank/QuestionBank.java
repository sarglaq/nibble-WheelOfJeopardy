package com.nibble.wheelofjeopardy.questionBank;

import java.io.IOException;
import java.util.Random;
//import java.net.URL;
import java.util.Vector;

import org.json.simple.parser.ParseException;

public class QuestionBank {
	//private URL file;
	private Vector<QuestionGroup> groups;
	private Vector<String> typePool;

	public QuestionBank(){
		//this.file = file;
		groups = new Vector<QuestionGroup>();
		typePool = new Vector<String>();
	}

	public void open() throws IOException, ParseException
	{
		int i = 0;
		int rdm = 0;
		Random rand = new Random();
		while(i < 6)
		{
			rdm = rand.nextInt(99)+1;// number should depends on the questions groups we have
			if(addGroup(new QuestionGroup(rdm)))
			{
				i += 1;
			}
		}
		// todo
	}

	public void close(){
		typePool.removeAllElements();
		groups.removeAllElements();
		// todo
	}

	public boolean addGroup(QuestionGroup questionGroup){
		// todo
		if(typePool.contains(questionGroup.type))
		{
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
		// todo
		return groups.get(groupID);
	}

	public Vector<QuestionGroup> getAllGroups(){
		// todo
		return groups;
	}

}