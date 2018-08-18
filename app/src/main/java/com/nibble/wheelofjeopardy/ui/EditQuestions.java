package com.nibble.wheelofjeopardy.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.nibble.wheelofjeopardy.R;
import com.nibble.wheelofjeopardy.questionBank.JSONHelper;
import com.nibble.wheelofjeopardy.questionBank.QuestionBank;
import com.nibble.wheelofjeopardy.questionBank.QuestionGroup;
import com.nibble.wheelofjeopardy.questionBoard.Category;

import java.util.Vector;

public class EditQuestions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_questions);

        Category category = Category.values()[this.getIntent().getIntExtra("CATEGORY", 1)-1];

        EditText categoryEdit = (EditText) findViewById(R.id.category_name);
        categoryEdit.setText(category.getName());

        QuestionBank bank = new QuestionBank();
        try {
            bank.open();
        } catch (Exception e) {
            System.out.println("Failed to open the question bank.");
            System.out.println(e);
        }

        final QuestionGroup thisGroup = bank.getGroup(category.getValue());

        for (int i = 1; i <= 5; i++) {
            EditText questionEdit = (EditText) findViewById(getEditQuestionID(i));
            questionEdit.setText(thisGroup.getQuestion(i).getQuestion());

            EditText answerEdit = (EditText) findViewById(getEditAnswerID(i));
            answerEdit.setText(thisGroup.getQuestion(i).getAnswer());
        }

        final Button saveButton = (Button) findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAndReturn(thisGroup);
            }
        });
    }

    private int getEditQuestionID(int i) {
        switch (i) {
            case 1: return R.id.question_1_input;
            case 2: return R.id.question_2_input;
            case 3: return R.id.question_3_input;
            case 4: return R.id.question_4_input;
            case 5: return R.id.question_5_input;
            default: return R.id.question_1_input;
        }
    }

    private int getEditAnswerID(int i) {
        switch (i) {
            case 1: return R.id.answer_1_input;
            case 2: return R.id.answer_2_input;
            case 3: return R.id.answer_3_input;
            case 4: return R.id.answer_4_input;
            case 5: return R.id.answer_5_input;
            default: return R.id.answer_1_input;
        }
    }

    private void saveAndReturn(QuestionGroup group) {
        EditText categoryName = findViewById(R.id.category_name);
        String categoryStringName = categoryName.getText().toString();
        try {
            JSONHelper.writeCategoryName(group.getId(), categoryStringName);
            Category.values()[group.getId()-1].setName(categoryStringName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Vector<String[]> questions = new Vector<>();

        for (int i = 1; i <= 5; i++) {
            EditText questionEdit = (EditText) findViewById(getEditQuestionID(i));
            EditText answerEdit = (EditText) findViewById(getEditAnswerID(i));

            String[] question = {questionEdit.getText().toString(), answerEdit.getText().toString()};
            questions.add(question);
        }

        try {
            // Make a new group with the same ID to write over the old group and "update" it
            QuestionGroup newGroup = new QuestionGroup(group.getId(), group.type, questions.get(0), questions.get(1), questions.get(2), questions.get(3), questions.get(4));
        } catch (Exception e) {
            System.out.println(e);
        }

        // Return to EditCategory activity
        Intent editCategory = new Intent(this, EditCategory.class);
        startActivity(editCategory);
    }
}
