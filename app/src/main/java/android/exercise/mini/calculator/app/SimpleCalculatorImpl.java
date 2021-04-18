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

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < this.history.size(); i++) {
      sb.append(this.history.get(i));
    }

    return sb.toString();
  }

  /**
   * insert a digit
   * @param digit - number between 0 to 9.
   */
  @Override
  public void insertDigit(int digit) throws RuntimeException{
    if ((digit < 0) || (9 < digit)){
      throw new RuntimeException();
    }
    this.history.add(String.valueOf(digit));
  }

  /**
   * insert a plus
   */
  @Override
  public void insertPlus() {
    if (this.history.isEmpty()){
      this.history.add("0");
    }
    else if (this.history.get(this.history.size() - 1).equals("+") ||
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
    if (this.history.isEmpty()){
      this.history.add("0");
    }
    else if (this.history.get(this.history.size() - 1).equals("+") ||
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
    StringBuilder curNum = new StringBuilder();
    ArrayList<Integer> numbersToCalc = new ArrayList<>();
    ArrayList<String> ops = new ArrayList<>();
    int result;

    if (this.history.isEmpty()){
      return;
    }

    for (int i = 0; i < this.history.size(); i++) {

      if (!this.history.get(i).equals("+") && !this.history.get(i).equals("-")){
        curNum.append(this.history.get(i));
      }

      else {
        numbersToCalc.add(Integer.parseInt(String.valueOf(curNum)));
        curNum.setLength(0);
        ops.add(this.history.get(i));
      }
    }

    if (curNum.length() != 0){
      numbersToCalc.add(Integer.parseInt(String.valueOf(curNum)));
      curNum.setLength(0);
    }

    result = numbersToCalc.get(0);
    for (int i = 0; i < numbersToCalc.size() - 1; i++) {
      if (ops.get(0).equals("+")){
        result += numbersToCalc.get(i + 1);
        ops.remove(0);
      }

      else if (ops.get(0).equals("-")){
        result -= numbersToCalc.get(i + 1);
        ops.remove(0);
      }
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
    state.copyOfHistory.addAll(this.history);
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
    this.history.clear();
    this.history.addAll(casted.copyOfHistory);
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
