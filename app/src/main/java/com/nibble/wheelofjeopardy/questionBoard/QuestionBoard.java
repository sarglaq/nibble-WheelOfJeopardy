package com.nibble.wheelofjeopardy.questionBoard;

import com.nibble.wheelofjeopardy.questionBank.Question;
import com.nibble.wheelofjeopardy.questionBank.QuestionBank;
import com.nibble.wheelofjeopardy.questionBank.QuestionGroup;

import java.util.Vector;

public class QuestionBoard {
    private final int numCategories = 6;
    private final int numQuestionsPerCategory = 5;
    private QuestionGroup[] categories = new QuestionGroup[numCategories];
    private boolean[][] questionAnswered = new boolean[numCategories][numQuestionsPerCategory];

    // todo fil board with actual questions
    public QuestionBoard() {
        /*
        for (int i = 0; i < numCategories; i++) {
            int cat = i + 1;
            String[] q1 = {"c" + cat + "q1", "c" + cat + "a1"};
            String[] q2 = {"c" + cat + "q2", "c" + cat + "a2"};
            String[] q3 = {"c" + cat + "q3", "c" + cat + "a3"};
            String[] q4 = {"c" + cat + "q4", "c" + cat + "a4"};
            String[] q5 = {"c" + cat + "q5", "c" + cat + "a5"};
            try {
                QuestionGroup newGroup = new QuestionGroup(i, "category" + i, q1, q2, q3, q4, q5, fileDirPath);
                System.out.println("made new group: " + newGroup);
                categories[i] = newGroup;
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        */
        QuestionBank bank = new QuestionBank();
        try {
            bank.open();
        } catch (Exception e) {
            System.out.println("Failed to open quesiton bank.");
            System.out.println(e);
        }
        Vector<QuestionGroup> categoryOptions = bank.getAllGroups();

        for (int i = 0; i < numCategories; i++) {
            categories[i] = categoryOptions.get(i);
            for (int j = 0; j < numQuestionsPerCategory; j++) {
                questionAnswered[i][j] = false;
            }
        }
    }

    public Question getNextQuestion(Category category) {
        System.out.println("Getting a question from " + category.getName());
        int categoryIndex = category.getValue() - 1;
        for (int j = 0; j < numQuestionsPerCategory; j++) {
            System.out.println("Checking if question " + j + " has been answered.");
            if (!questionAnswered[categoryIndex][j]) {
                System.out.println("Question " + j + " has not been answered, returning.");
                questionAnswered[categoryIndex][j] = true;
                return categories[categoryIndex].getQuestion(j+1);
            }
        }
        return null;
    }

    public int getRemainingQuesitons() {
        int numRemaining = 0;
        for (int i = 0; i < numCategories; i++) {
            for (int j = 0; j < numQuestionsPerCategory; j++) {
                if (!questionAnswered[i][j]) {
                    numRemaining++;
                }
            }
        }
        return numRemaining;
    }

    public boolean anyQuestionsRemaining(Category category) {
        for (int i = 0; i < numQuestionsPerCategory; i++) {
            if (!questionAnswered[category.getValue()-1][i]) {
                return true;
            }
        }
        return false;
    }

    public void reset() {
        for (int i = 0; i < numCategories; i++) {
            for (int j = 0; j < numQuestionsPerCategory; j++) {
                questionAnswered[i][j] = false;
            }
        }
    }
}
