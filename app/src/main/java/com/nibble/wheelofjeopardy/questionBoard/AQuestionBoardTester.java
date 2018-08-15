package com.nibble.wheelofjeopardy.questionBoard;

import com.nibble.wheelofjeopardy.questionBank.JSONHelper;
import com.nibble.wheelofjeopardy.questionBank.QuestionBank;
import com.nibble.wheelofjeopardy.questionBank.QuestionGroup;

import java.io.IOException;

import org.json.simple.parser.ParseException;

public class AQuestionBoardTester
{

    public AQuestionBoardTester() {
        // TODO Auto-generated constructor stub
    }

    public static void main(String[] args) throws IOException, ParseException
    {
        /**
         * random creator
         */
        JSONHelper jhp = new JSONHelper("temp/");
        for(int i = 1; i <= 100; i += 1)
        {
            String type = i+"type";
            String[] q1 = new String[] {i+"q1",i+"a1"};
            String[] q2 = new String[] {i+"q2",i+"a2"};
            String[] q3 = new String[] {i+"q3",i+"a3"};
            String[] q4 = new String[] {i+"q4",i+"a4"};
            String[] q5 = new String[] {i+"q5",i+"a5"};
            jhp.writeQuestionGroup(i, type, q1, q2, q3, q4, q5);
        }

        QuestionBank qb = new QuestionBank("temp/");
        qb.open();
        for(QuestionGroup qg: qb.getAllGroups())
        {
            System.out.print(qg.getQuestion(1).getQuestion()+" ");
            System.out.print(qg.getQuestion(1).getAnswer()+"  ");
            System.out.print(qg.getQuestion(2).getQuestion()+" ");
            System.out.print(qg.getQuestion(2).getAnswer()+"  ");
            System.out.print(qg.getQuestion(3).getQuestion()+"  ");
            System.out.print(qg.getQuestion(3).getAnswer()+"  ");
            System.out.print(qg.getQuestion(4).getQuestion()+" ");
            System.out.print(qg.getQuestion(4).getAnswer()+"  ");
            System.out.print(qg.getQuestion(5).getQuestion()+" ");
            System.out.println(qg.getQuestion(5).getAnswer()+"  ");
        }

        qb.close();
        jhp.getID(1);
        jhp.writeAnswer(1, "new Answer1");
        jhp.getID(2);
        jhp.writeAnswer(2, "new Answer2");
        jhp.getID(3);
        jhp.writeAnswer(3, "new Answer3");
        jhp.getID(4);
        jhp.writeAnswer(4, "new Answer4");
        jhp.getID(5);
        jhp.writeAnswer(5, "new Answer5");
        jhp.getID(6);
        jhp.writeAnswer(1, "new Answer6");
        qb.addGroup(new QuestionGroup(1, "temp/"));
        qb.addGroup(new QuestionGroup(2, "temp/"));
        qb.addGroup(new QuestionGroup(3, "temp/"));
        qb.addGroup(new QuestionGroup(4, "temp/"));
        qb.addGroup(new QuestionGroup(5, "temp/"));
        qb.addGroup(new QuestionGroup(6, "temp/"));
        for(QuestionGroup qg: qb.getAllGroups())
        {
            System.out.print(qg.getQuestion(1).getQuestion()+" ");
            System.out.print(qg.getQuestion(1).getAnswer()+"  ");
            System.out.print(qg.getQuestion(2).getQuestion()+" ");
            System.out.print(qg.getQuestion(2).getAnswer()+"  ");
            System.out.print(qg.getQuestion(3).getQuestion()+"  ");
            System.out.print(qg.getQuestion(3).getAnswer()+"  ");
            System.out.print(qg.getQuestion(4).getQuestion()+" ");
            System.out.print(qg.getQuestion(4).getAnswer()+"  ");
            System.out.print(qg.getQuestion(5).getQuestion()+" ");
            System.out.println(qg.getQuestion(5).getAnswer()+"  ");
        }
    }

}
