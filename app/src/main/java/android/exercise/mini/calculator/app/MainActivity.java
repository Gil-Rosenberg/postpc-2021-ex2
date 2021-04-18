package android.exercise.mini.calculator.app;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

  @VisibleForTesting
  public SimpleCalculator calculator;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    if (calculator == null) {
      calculator = new SimpleCalculatorImpl();
    }

    // 1. find all views
    TextView textViewCalculatorOutput = findViewById(R.id.textViewCalculatorOutput);
    textViewCalculatorOutput.setVisibility(View.VISIBLE);

    // buttons:
    TextView buttonEquals = findViewById(R.id.buttonEquals);
    buttonEquals.setVisibility(View.VISIBLE);

    TextView buttonPlus = findViewById(R.id.buttonPlus);
    buttonPlus.setVisibility(View.VISIBLE);

    TextView buttonMinus = findViewById(R.id.buttonMinus);
    buttonMinus.setVisibility(View.VISIBLE);

    TextView buttonClear = findViewById(R.id.buttonClear);
    buttonClear.setVisibility(View.VISIBLE);

    TextView button0 = findViewById(R.id.button0);
    button0.setVisibility(View.VISIBLE);

    TextView button1 = findViewById(R.id.button1);
    button1.setVisibility(View.VISIBLE);

    TextView button2 = findViewById(R.id.button2);
    button2.setVisibility(View.VISIBLE);

    TextView button3 = findViewById(R.id.button3);
    button3.setVisibility(View.VISIBLE);

    TextView button4 = findViewById(R.id.button4);
    button4.setVisibility(View.VISIBLE);

    TextView button5 = findViewById(R.id.button5);
    button5.setVisibility(View.VISIBLE);

    TextView button6 = findViewById(R.id.button6);
    button6.setVisibility(View.VISIBLE);

    TextView button7 = findViewById(R.id.button7);
    button7.setVisibility(View.VISIBLE);

    TextView button8 = findViewById(R.id.button8);
    button8.setVisibility(View.VISIBLE);

    TextView button9 = findViewById(R.id.button9);
    button9.setVisibility(View.VISIBLE);

    View buttonBackSpace = findViewById(R.id.buttonBackSpace);
    buttonBackSpace.setVisibility(View.VISIBLE);

//    View spaceBelowButton1 = findViewById(R.id.spaceBelowButton1);
//    ImageView backSpaceImage = findViewById(R.id.backSpaceImage);

    // 2. initial update main text-view based on calculator's output
    textViewCalculatorOutput.setText(calculator.output());

    // 3. set click listeners on all buttons to operate on the calculator and refresh main text-view
    buttonEquals.setOnClickListener(v -> {
      calculator.insertEquals();
      textViewCalculatorOutput.setText(calculator.output());
    });

    buttonPlus.setOnClickListener(v -> {
      calculator.insertPlus();
      textViewCalculatorOutput.setText(calculator.output());
    });

    buttonMinus.setOnClickListener(v -> {
      calculator.insertMinus();
      textViewCalculatorOutput.setText(calculator.output());
    });

    buttonClear.setOnClickListener(v -> {
      calculator.clear();
      textViewCalculatorOutput.setText(calculator.output());
    });

    button0.setOnClickListener(v -> {
      calculator.insertDigit(0);
      textViewCalculatorOutput.setText(calculator.output());
    });

    button1.setOnClickListener(v -> {
      calculator.insertDigit(1);
      textViewCalculatorOutput.setText(calculator.output());
    });

    button2.setOnClickListener(v -> {
      calculator.insertDigit(2);
      textViewCalculatorOutput.setText(calculator.output());
    });

    button3.setOnClickListener(v -> {
      calculator.insertDigit(3);
      textViewCalculatorOutput.setText(calculator.output());
    });

    button4.setOnClickListener(v -> {
      calculator.insertDigit(4);
      textViewCalculatorOutput.setText(calculator.output());
    });

    button5.setOnClickListener(v -> {
      calculator.insertDigit(5);
      textViewCalculatorOutput.setText(calculator.output());
    });

    button6.setOnClickListener(v -> {
      calculator.insertDigit(6);
      textViewCalculatorOutput.setText(calculator.output());
    });

    button7.setOnClickListener(v -> {
      calculator.insertDigit(7);
      textViewCalculatorOutput.setText(calculator.output());
    });

    button8.setOnClickListener(v -> {
      calculator.insertDigit(8);
      textViewCalculatorOutput.setText(calculator.output());
    });

    button9.setOnClickListener(v -> {
      calculator.insertDigit(9);
      textViewCalculatorOutput.setText(calculator.output());
    });

    buttonBackSpace.setOnClickListener(v -> {
      calculator.deleteLast();
      textViewCalculatorOutput.setText(calculator.output());
    });
  }

  /**
   * save calculator state into the bundle
   * @param outState - Bundle
   */
  @Override
  protected void onSaveInstanceState(@NonNull Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putSerializable("calculator state", calculator.saveState());
  }

  /**
   * restore calculator state from the bundle, refresh main text-view from calculator's output
   * @param savedInstanceState - Bundle
   */
  @Override
  protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
    Serializable prevState = savedInstanceState.getSerializable("calculator state");
    calculator.loadState(prevState);
  }
}