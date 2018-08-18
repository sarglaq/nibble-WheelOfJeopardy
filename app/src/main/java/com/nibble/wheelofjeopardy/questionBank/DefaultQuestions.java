package com.nibble.wheelofjeopardy.questionBank;

import com.nibble.wheelofjeopardy.questionBoard.Category;

import org.json.simple.parser.ParseException;

import java.io.IOException;

public class DefaultQuestions {
    private DefaultQuestions() {
        // do nothing
    }

    public static QuestionGroup getDefaultCategory1() throws IOException, ParseException
    {
        String[] q1 = {"The legislative assembly established in the 1640's in Virginia now known as the General Assembly of Virginia was the", "House of Burgesses"};
        String[] q2 = {"The middle colonies had more diverse lifestyles than New England or the Southern colonies because they had a greater variety of:", "cultural groups"};
        String[] q3 = {"Early European contact devastated Native American populations by introducing", "infectious disease"};
        String[] q4 = {"The main economic activities of the New England colonies were", "shipbuilding and fishing"};
        String[] q5 = {"The Great Awakening of the 18th Century was a(n)", "religious revival"};
        return new QuestionGroup(1, "History", q1, q2, q3, q4, q5);
    }

    public static QuestionGroup getDefaultCategory2() throws IOException, ParseException
    {
        String[] q1 = {"What two stellar objects can arise from a supernova?", "Neutron star and black hole"};
        String[] q2 = {"What evidence do we have of the inflation right after the Big Bang?", "Cosmic Background radiation"};
        String[] q3 = {"When does a star leave the main sequence?", "When it runs out of hydrogen to fuse"};
        String[] q4 = {"What two steller objects come after a red giant in the life cycle of stars?", "Planetary nebula and white dwarf"};
        String[] q5 = {"Why are certain telescopes in space?", "EM waves are blocked"};
        return new QuestionGroup(2, "Astronomy", q1, q2, q3, q4, q5);
    }

    public static QuestionGroup getDefaultCategory3() throws IOException, ParseException
    {
        String[] q1 = {"Brass gets discoloured in air because of the presence of which of the following gases in air?", "Hydrogen sulphide"};
        String[] q2 = {"Which of the following is a non metal that remains liquid at room temperature?", "Bromine"};
        String[] q3 = {"Chlorophyll is a naturally occurring chelate compound in which central metal is", "Magnesium"};
        String[] q4 = {"Which of the following is used in pencils?", "Graphite"};
        String[] q5 = {"Which of the following metals forms an amalgam with other metals?", "Mercury"};
        return new QuestionGroup(3, "Chemistry", q1, q2, q3, q4, q5);
    }

    public static QuestionGroup getDefaultCategory4() throws IOException, ParseException
    {
        String[] q1 = {"The Rio Hondo forms a boundary between Mexico and which other country?", "Belize"};
        String[] q2 = {"Which lake is on the border between Chad and Cameroon?", "Lake Chad"};
        String[] q3 = {"Which country is the United States largest trading partner?", "Canada"};
        String[] q4 = {"La Paz is the administrative capital of Bolivia. What is the constitutional capital?", "Sucre"};
        String[] q5 = {"Which mountain range is the traditional division between Europe and Asia?", "Ural Mountains"};
        return new QuestionGroup(4, "Geography", q1, q2, q3, q4, q5);
    }

    public static QuestionGroup getDefaultCategory5() throws IOException, ParseException
    {
        String[] q1 = {"Who was the author of the famous storybook 'Alice's Adventures in Wonderland'?", "Lewis Carroll"};
        String[] q2 = {"Who wrote the famous 1855 poem 'The Charge of the Light Brigade'?", "Lord Alfred Tennyson"};
        String[] q3 = {"Who wrote 'Where ignorance is bliss. it is folly to be wise'?", "Shakespeare"};
        String[] q4 = {"What was the nationality of Robert Louis Stevenson", "Scottish"};
        String[] q5 = {"Jane Eyre' was written by which Bronte sister?", "Charlotte"};
        return new QuestionGroup(5, "Literature", q1, q2, q3, q4, q5);
    }

    public static QuestionGroup getDefaultCategory6() throws IOException, ParseException
    {
        String[] q1 = {"Which country is known as ‘Economic Power House of Europe’?", "Germany"};
        String[] q2 = {"Who wrote Galileo‘s Daughter?", "Dava Sobel."};
        String[] q3 = {"Which day is celebrated as International Customs Day?", "January 26."};
        String[] q4 = {"Name the highest town of Europe?", "Daos."};
        String[] q5 = {"Who discovered Richter scale?", "Charles F. Richter."};
        return new QuestionGroup(6, "General", q1, q2, q3, q4, q5);
    }
}
