package com.example.guessmynumbergame;



import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int randomNumber;
    private int maxTries = 5;
    private int currentTries = 0;

    private TextView feedbackText;
    private EditText guessInput;
    private Button guessButton;
    private Button resetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        feedbackText = findViewById(R.id.feedbackText);
        guessInput = findViewById(R.id.guessInput);
        guessButton = findViewById(R.id.guessButton);
        resetButton = findViewById(R.id.resetButton);


        generateRandomNumber();

        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guessNumber();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
    }

    private void generateRandomNumber() {
        Random rand = new Random();
        randomNumber = rand.nextInt(100) + 1;
    }

    private void guessNumber() {
        String guessString = guessInput.getText().toString();

        if (!guessString.isEmpty()) {
            int userGuess = Integer.parseInt(guessString);
            currentTries++;

            if (userGuess == randomNumber) {
                feedbackText.setText("Correct! You've guessed the number.");
                guessButton.setEnabled(false);
                resetButton.setVisibility(View.VISIBLE);
            } else if (userGuess > randomNumber) {
                feedbackText.setText("Too high! Try again.");
                guessInput.setText("");  // Clear the input field after a wrong guess
            } else {
                feedbackText.setText("Too low! Try again.");
                guessInput.setText("");  // Clear the input field after a wrong guess
            }

            if (currentTries >= maxTries && userGuess != randomNumber) {
                feedbackText.setText("You've used all your attempts. The number was: " + randomNumber);
                guessButton.setEnabled(false);
                resetButton.setVisibility(View.VISIBLE);
            }

        } else {
            Toast.makeText(this, "Please enter a guess.", Toast.LENGTH_SHORT).show();
        }
    }

    private void resetGame() {
        // Reset attempts
        currentTries = 0;

        // Generate a new random number
        generateRandomNumber();

        // Clear feedback and input
        feedbackText.setText("");
        guessInput.setText("");

        // Re-enable the guess button
        guessButton.setEnabled(true);
        resetButton.setVisibility(View.GONE);
    }
}



