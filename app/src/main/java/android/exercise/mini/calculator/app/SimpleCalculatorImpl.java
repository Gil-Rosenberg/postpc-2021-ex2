package android.exercise.mini.calculator.app;

import android.text.TextUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;


public class SimpleCalculatorImpl implements SimpleCalculator {

  private final ArrayList<String> history = new ArrayList<>();

  /**
   *
   * @return output based on the current state
   */
  @Override
  public String output() {
    if (this.history.isEmpty()){
      return "0";
    }
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
    if (this.history.get(this.history.size() - 1).equals("+") ||
            this.history.get(this.history.size() - 1).equals("-")){
      return; // ignore
    }
    this.history.add("+");
  }

  /**
   * insert a minus
   */
  @Override
  public void insertMinus() {
    if (this.history.get(this.history.size() - 1).equals("+") ||
            this.history.get(this.history.size() - 1).equals("-")){
      return; // ignore
    }
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

  /**
   * delete the last input (digit, plus or minus)
   * if input was "12+3" and called `deleteLast()`, then delete the "3"
   * if input was "12+" and called `deleteLast()`, then delete the "+"
   * if no input was given, then there is nothing to do here
   */
  @Override
  public void deleteLast() {
    if (this.history.isEmpty()){
      return; // ignore
    }
    this.history.remove(this.history.size() - 1);
  }

  /**
   * clear everything (same as no-input was never given)
   */
  @Override
  public void clear() {
    this.history.clear();
  }

  /**
   * insert all data to the state, so in the future we can load from this state
   * @return Serializable
   */
  @Override
  public Serializable saveState() {
    CalculatorState state = new CalculatorState();
    Collections.copy(state.copyOfHistory, this.history);
    return state;
  }

  /**
   * use the CalculatorState to load
   * @param prevState the state to load
   */
  @Override
  public void loadState(Serializable prevState) {
    if (!(prevState instanceof CalculatorState)) {
      return; // ignore
    }
    CalculatorState casted = (CalculatorState) prevState;
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
