package com.nibble.wheelofjeopardy.questionBank;

import java.net.URL;
import java.util.Vector;

public class QuestionBank {
	private URL file;
	private Vector<QuestionGroup> groups;

	public QuestionBank(URL file){
		this.file = file;
	}
	
	public void open(){
	    // todo
    }
	
	public void close(){
	    // todo
    }
	
	public void addGroup(QuestionGroup questionGroup){
	    // todo
    }
	
	public void removeGroup(QuestionGroup questionGroup){
	    // todo
    }
	
	public QuestionGroup getGroup(int groupID){
	    // todo
        return null;
    }
	
	public Vector<QuestionGroup> getAllGroups(){
	    // todo
        return null;
    }
	
}