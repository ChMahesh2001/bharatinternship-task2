package com.example.satya3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView questionTextView;
    private RadioGroup answerRadioGroup;
    private RadioButton option1RadioButton, option2RadioButton, option3RadioButton;
    private Button submitButton;

    private String[] questions = {"What is the capital of France?", "Which planet is known as the Red Planet?", "Who wrote Romeo and Juliet?"};
    private String[] correctAnswers = {"Paris", "Mars", "William Shakespeare"};
    private int currentQuestionIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionTextView = findViewById(R.id.questionTextView);
        answerRadioGroup = findViewById(R.id.answerRadioGroup);
        option1RadioButton = findViewById(R.id.option1RadioButton);
        option2RadioButton = findViewById(R.id.option2RadioButton);
        option3RadioButton = findViewById(R.id.option3RadioButton);
        submitButton = findViewById(R.id.submitButton);

        displayQuestion();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer();
            }
        });
    }

    private void displayQuestion() {
        questionTextView.setText(questions[currentQuestionIndex]);
        option1RadioButton.setText("1."+correctAnswers[currentQuestionIndex]);
        option2RadioButton.setText("2."+"Random option");
        option3RadioButton.setText("3."+"Another Random option");
    }

    private void checkAnswer() {
        RadioButton selectedRadioButton = findViewById(answerRadioGroup.getCheckedRadioButtonId());
        if (selectedRadioButton != null) {
            String selectedAnswer = selectedRadioButton.getText().toString();
            String correctAnswer = correctAnswers[currentQuestionIndex];

            String feedback;
            if (selectedAnswer.equals(correctAnswer)) {
                feedback = "Correct!";
            } else {
                feedback = "Incorrect. The correct answer is: " + correctAnswer;
            }

            // Show feedback to the user (you can use a Toast or a Dialog)
            // For simplicity, we'll use a Toast here
            // In a real-world app, you might want to use AlertDialog for better user experience

            // Toast.makeText(MainActivity.this, feedback, Toast.LENGTH_SHORT).show();

            // Display next question or finish the quiz
            if (currentQuestionIndex < questions.length - 1) {
                currentQuestionIndex++;
                displayQuestion();
                answerRadioGroup.clearCheck();
            } else {
                // End of the quiz
                questionTextView.setText("Quiz completed!");
                answerRadioGroup.setVisibility(View.GONE);
                submitButton.setVisibility(View.GONE);
            }
        }
    }
}
