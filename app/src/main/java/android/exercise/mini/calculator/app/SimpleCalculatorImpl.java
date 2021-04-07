package android.exercise.mini.calculator.app;

import android.text.TextUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;


public class SimpleCalculatorImpl implements SimpleCalculator {

  // todo: add fields as needed

  private final ArrayList<String> history = new ArrayList<>();

  /**
   *
   * @return output based on the current state
   */
  @Override
  public String output() {
    return TextUtils.join("", this.history);
  }

  /**
   * insert a digit
   * @param digit - number between 0 to 9.
   */
  @Override
  public void insertDigit(int digit) {
    this.history.add(String.valueOf(digit));
  }

  /**
   * insert a plus
   */
  @Override
  public void insertPlus() {
    this.history.add("+");
  }

  /**
   * insert a minus
   */
  @Override
  public void insertMinus() {
    this.history.add("-");
  }

  /**
   * calculate the equation. after calling `insertEquals()`, the output should be the result.
   * e.g. given input "14+3", calling `insertEquals()`, and calling `output()`, output should be "17"
   */
  @Override
  public void insertEquals() {
    int result = 0;
    boolean minus = false, plus = false;
    for (String str: this.history) {
      if (str.equals("+")) {
        plus = true;
        minus = false;
        continue;
      }

      if (str.equals("-")){
        minus = true;
        plus = false;
        continue;
      }

      if (plus){
        result += Integer.parseInt(str);
        plus = false;
        continue;
      }

      else if (minus){
        result -= Integer.parseInt(str);
        minus = false;
        continue;
      }
      result = Integer.parseInt(str);
    }

    this.history.clear();   // erase calculations
    this.history.add(String.valueOf(result));   // put the result
  }

  @Override
  public void deleteLast() {
    // todo: delete the last input (digit, plus or minus)
    //  e.g.
    //  if input was "12+3" and called `deleteLast()`, then delete the "3"
    //  if input was "12+" and called `deleteLast()`, then delete the "+"
    //  if no input was given, then there is nothing to do here
    this.history.remove(this.history.size() - 1);
  }

  @Override
  public void clear() {
    // todo: clear everything (same as no-input was never given)
    this.history.clear();
    this.history.add("0");
  }

  @Override
  public Serializable saveState() {
    CalculatorState state = new CalculatorState();
    // todo: insert all data to the state, so in the future we can load from this state
    Collections.copy(state.copyOfHistory, this.history);
    return state;
  }

  @Override
  public void loadState(Serializable prevState) {
    if (!(prevState instanceof CalculatorState)) {
      return; // ignore
    }
    CalculatorState casted = (CalculatorState) prevState;
    // todo: use the CalculatorState to load
    Collections.copy(this.history, casted.copyOfHistory);
  }

  private static class CalculatorState implements Serializable {
    /*
    TODO: add fields to this class that will store the calculator state
    all fields must only be from the types:
    - primitives (e.g. int, boolean, etc)
    - String
    - ArrayList<> where the type is a primitive or a String
    - HashMap<> where the types are primitives or a String
     */
    private final ArrayList<String> copyOfHistory = new ArrayList<>();
  }
}
