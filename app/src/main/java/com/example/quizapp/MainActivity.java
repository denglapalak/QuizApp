package com.example.quizapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView questionTextView;
    private RadioGroup optionsRadioGroup;
    private Button submitButton;
    private Button nextButton;
    private TextView resultTextView;

    private String currentCorrectAnswer;
    private int index;

    private String[] questions = {
            "What is the capital of France?",
            "Which planet is known as the Red Planet?",
            "Who wrote 'Romeo and Juliet'?",
            "What is the largest mammal in the world?"
    };

    private String[][] options = {
            {"A. London", "B. Berlin", "C. Paris"},
            {"A. Venus", "B. Mars", "C. Jupiter"},
            {"A. Charles Dickens", "B. William Shakespeare", "C. Jane Austen"},
            {"A. Elephant", "B. Blue Whale", "C. Giraffe"}
    };

    private String[] correctAnswers = {"C. Paris", "B. Mars", "B. William Shakespeare", "B. Blue Whale"};

    private int currentQuestionIndex = 0;
    private int totalMarks = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        questionTextView = findViewById(R.id.questionTextView);
        optionsRadioGroup = findViewById(R.id.optionsRadioGroup);
        submitButton = findViewById(R.id.submitButton);
        nextButton = findViewById(R.id.nextButton);
        resultTextView = findViewById(R.id.resultTextView);

        // Set the initial question
        setQuestion(currentQuestionIndex);

        // Set the onClickListeners
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentQuestionIndex < questions.length - 1) {
                    currentQuestionIndex++;
                    setQuestion(currentQuestionIndex);
                } else {
                    // Display total marks when there are no more questions
                    resultTextView.setText("Total Marks: " + totalMarks + " out of " + questions.length);
                    submitButton.setEnabled(false);
                    nextButton.setEnabled(false);
                }
            }
        });
    }

    private void setQuestion(int index) {
        // Set the question text
        questionTextView.setText(questions[index]);

        // Clear any previous selections in the RadioGroup
        optionsRadioGroup.clearCheck();

        // Set the options for the current question
        RadioButton option1 = findViewById(R.id.option1RadioButton);
        RadioButton option2 = findViewById(R.id.option2RadioButton);
        RadioButton option3 = findViewById(R.id.option3RadioButton);

        option1.setText(options[index][0]);
        option2.setText(options[index][1]);
        option3.setText(options[index][2]);

        // Set the correct answer for the current question
        currentCorrectAnswer = correctAnswers[index];

        // Clear the result text
        resultTextView.setText("");
    }



    private void checkAnswer() {
        // Get the selected RadioButton ID from the RadioGroup
        int selectedOptionId = optionsRadioGroup.getCheckedRadioButtonId();

        if (selectedOptionId != -1) {
            // Find the RadioButton by ID
            RadioButton selectedRadioButton = findViewById(selectedOptionId);

            // Get the text of the selected RadioButton
            String selectedAnswer = selectedRadioButton.getText().toString();

            // Get the correct answer for the current question
            String currentCorrectAnswer = correctAnswers[currentQuestionIndex];

            // Check if the selected answer is correct
            if (selectedAnswer.equals(currentCorrectAnswer)) {
                totalMarks++;
                resultTextView.setText("Correct! Total Marks: " + totalMarks);
            } else {
                resultTextView.setText("Incorrect. The correct answer is " + currentCorrectAnswer + ". Total Marks: " + totalMarks);
            }

            // Increment the index to move to the next question
            currentQuestionIndex++;

            // Check if there are more questions or not
            if (currentQuestionIndex < questions.length) {
                setQuestion(currentQuestionIndex);
            } else {
                // Display total marks when there are no more questions
                resultTextView.setText("Total Marks: " + totalMarks + " out of " + questions.length);
                submitButton.setEnabled(false);
                nextButton.setEnabled(false);
            }
        } else {
            // No option selected
            Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show();
        }
    }




}



