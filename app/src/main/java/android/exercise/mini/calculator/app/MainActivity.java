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
    TextView buttonPlus = findViewById(R.id.buttonPlus);
    TextView buttonMinus = findViewById(R.id.buttonMinus);
    TextView buttonClear = findViewById(R.id.buttonClear);
    TextView button0 = findViewById(R.id.button0);
    TextView button1 = findViewById(R.id.button1);
    TextView button2 = findViewById(R.id.button2);
    TextView button3 = findViewById(R.id.button3);
    TextView button4 = findViewById(R.id.button4);
    TextView button5 = findViewById(R.id.button5);
    TextView button6 = findViewById(R.id.button6);
    TextView button7 = findViewById(R.id.button7);
    TextView button8 = findViewById(R.id.button8);
    TextView button9 = findViewById(R.id.button9);
    View buttonBackSpace = findViewById(R.id.buttonBackSpace);
//    View spaceBelowButton1 = findViewById(R.id.spaceBelowButton1);
//    ImageView backSpaceImage = findViewById(R.id.backSpaceImage);

    // 2. initial update main text-view based on calculator's output
    textViewCalculatorOutput.setText("0");

    // 3. set click listeners on all buttons to operate on the calculator and refresh main text-view
    buttonEquals.setOnClickListener(v -> {
      calculator.insertEquals();
      textViewCalculatorOutput.setText(calculator.output());
    });

    buttonPlus.setOnClickListener(v -> calculator.insertPlus());

    buttonMinus.setOnClickListener(v -> calculator.insertMinus());

    buttonClear.setOnClickListener(v -> calculator.clear());

    button0.setOnClickListener(v -> calculator.insertDigit(0));

    button1.setOnClickListener(v -> calculator.insertDigit(1));

    button2.setOnClickListener(v -> calculator.insertDigit(2));

    button3.setOnClickListener(v -> calculator.insertDigit(3));

    button4.setOnClickListener(v -> calculator.insertDigit(4));

    button5.setOnClickListener(v -> calculator.insertDigit(5));

    button6.setOnClickListener(v -> calculator.insertDigit(6));

    button7.setOnClickListener(v -> calculator.insertDigit(7));

    button8.setOnClickListener(v -> calculator.insertDigit(8));

    button9.setOnClickListener(v -> calculator.insertDigit(9));

    buttonBackSpace.setOnClickListener(v -> calculator.deleteLast());
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