package com.nibble.wheelofjeopardy.questionBoard;

import com.nibble.wheelofjeopardy.questionBank.Question;
import com.nibble.wheelofjeopardy.questionBank.QuestionGroup;

public class QuestionBoard {
    private final int numCategories = 6;
    private final int numQuestionsPerCategory = 5;
    private QuestionGroup[] categories = new QuestionGroup[numCategories];
    private boolean[][] questionAnswered = new boolean[numCategories][numQuestionsPerCategory];

    // todo fil board with actual questions
    public QuestionBoard(String fileDirPath) {
        for (int i = 0; i < numCategories; i++) {
            String[] q1 = {"c" + i + "q1", "c" + i + "a1"};
            String[] q2 = {"c" + i + "q2", "c" + i + "a2"};
            String[] q3 = {"c" + i + "q3", "c" + i + "a3"};
            String[] q4 = {"c" + i + "q4", "c" + i + "a4"};
            String[] q5 = {"c" + i + "q5", "c" + i + "a5"};
            try {
                QuestionGroup newGroup = new QuestionGroup(i, "category" + i, q1, q2, q3, q4, q5, fileDirPath);
                System.out.println("made new group: " + newGroup);
                categories[i] = newGroup;
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        for (int i = 0; i < numCategories; i++) {
            for (int j = 0; j < numQuestionsPerCategory; j++) {
                questionAnswered[i][j] = false;
            }
        }
    }

    public Question getNextQuestion(Category category) {
        int categoryIndex = category.getValue() - 1;
        for (int j = 0; j < numQuestionsPerCategory; j++) {
            if (!questionAnswered[categoryIndex][j]) {
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

    public void reset() {
        for (int i = 0; i < numCategories; i++) {
            for (int j = 0; j < numQuestionsPerCategory; j++) {
                questionAnswered[i][j] = false;
            }
        }
    }
}
