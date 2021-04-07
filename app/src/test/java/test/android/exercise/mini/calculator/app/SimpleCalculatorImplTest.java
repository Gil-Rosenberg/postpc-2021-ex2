package test.android.exercise.mini.calculator.app;

import android.exercise.mini.calculator.app.SimpleCalculatorImpl;

import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class SimpleCalculatorImplTest {

  @Test
  public void when_noInputGiven_then_outputShouldBe0(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    assertEquals("0", calculatorUnderTest.output());
  }

  @Test
  public void when_inputIsPlus_then_outputShouldBe0Plus(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertPlus();
    assertEquals("0+", calculatorUnderTest.output());
  }

  @Test
  public void when_inputIsMinus_then_outputShouldBeCorrect(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertMinus();
    String expected = "0-";
    assertEquals(expected, calculatorUnderTest.output());
  }

  @Test
  public void when_callingInsertDigitWithIllegalNumber_then_exceptionShouldBeThrown(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    try {
      calculatorUnderTest.insertDigit(357);
      fail("should throw an exception and not reach this line");
    } catch (RuntimeException e) {
      // good :)
    }
  }

  @Test
  public void when_callingDeleteLast_then_lastOutputShouldBeDeleted(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();

    // insert numbers:
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertDigit(2);

    // delete last:
    calculatorUnderTest.deleteLast();
    assertEquals("1", calculatorUnderTest.output());
  }

  @Test
  public void when_callingClear_then_outputShouldBeCleared(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();

    // insert numbers:
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertDigit(2);

    // delete last:
    calculatorUnderTest.clear();
    assertEquals("0", calculatorUnderTest.output());
  }

  @Test
  public void when_savingState_should_loadThatStateCorrectly(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    // give some input
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(7);

    // save current state
    Serializable savedState = calculatorUnderTest.saveState();
    assertNotNull(savedState);

    // call `clear` and make sure calculator cleared
    calculatorUnderTest.clear();
    assertEquals("0", calculatorUnderTest.output());

    // load the saved state and make sure state was loaded correctly
    calculatorUnderTest.loadState(savedState);
    assertEquals("5+7", calculatorUnderTest.output());
  }

  @Test
  public void when_savingStateFromFirstCalculator_should_loadStateCorrectlyFromSecondCalculator(){
    SimpleCalculatorImpl firstCalculator = new SimpleCalculatorImpl();
    SimpleCalculatorImpl secondCalculator = new SimpleCalculatorImpl();

    // give some input
    firstCalculator.insertDigit(5);
    firstCalculator.insertPlus();
    firstCalculator.insertDigit(7);

    // save current state
    Serializable savedState = firstCalculator.saveState();
    assertNotNull(savedState);

    // load the saved state and make sure state was loaded correctly
    secondCalculator.loadState(savedState);
    assertEquals("5+7", secondCalculator.output());
  }

  // ----------------------- MY TESTS -----------------------

  @Test
  public void deleteLast_extreme(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();

    // give some input
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertDigit(7);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertDigit(3);

    // delete last
    calculatorUnderTest.deleteLast();

    // give some more input
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.insertDigit(5);

    assertEquals("5+17-125", calculatorUnderTest.output());
  }

  @Test
  public void clearExtreme(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();

    // give some input
    calculatorUnderTest.insertDigit(9);   // 9

    // clear
    calculatorUnderTest.clear();          // 0

    // give some more input
    calculatorUnderTest.insertDigit(1);   // 1
    calculatorUnderTest.insertDigit(2);   // 12

    // clear
    calculatorUnderTest.clear();          // 0

    // give some more input
    calculatorUnderTest.insertDigit(8);   // 8
    calculatorUnderTest.insertMinus();    // 8-
    calculatorUnderTest.insertDigit(7);   // 8-7
    calculatorUnderTest.insertEquals();   // 1

    assertEquals("1", calculatorUnderTest.output());
  }

  @Test
  public void complicatedCalculations1(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();

    // give some input
    calculatorUnderTest.insertDigit(8);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(7);
    calculatorUnderTest.insertEquals(); // = 1

    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(4);
    calculatorUnderTest.insertEquals(); // = 5
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertEquals(); // = 4
    assertEquals("4", calculatorUnderTest.output());
  }

  @Test
  public void complicatedCalculations2(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();

    // give some input
    calculatorUnderTest.insertDigit(9);
    calculatorUnderTest.insertDigit(9);
    calculatorUnderTest.insertDigit(9);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(8);
    calculatorUnderTest.insertDigit(8);
    calculatorUnderTest.insertDigit(8); // = 111
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.insertEquals(); // = -111
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(3);
    calculatorUnderTest.insertDigit(3);
    calculatorUnderTest.insertDigit(3);
    assertEquals("-111-333", calculatorUnderTest.output());
  }

  @Test
  public void different_inputs_two_calculators_saveStateOnFirst_loadIntoSecond(){
    SimpleCalculatorImpl firstCalculator = new SimpleCalculatorImpl();
    SimpleCalculatorImpl secondCalculator = new SimpleCalculatorImpl();

    // give the first some input
    firstCalculator.insertDigit(1);
    firstCalculator.insertDigit(1);
    firstCalculator.insertDigit(3);
    firstCalculator.insertMinus();
    firstCalculator.insertDigit(1);
    firstCalculator.insertDigit(3);
    firstCalculator.insertEquals(); // =100

    // save current state of first
    Serializable savedStateOfFirst = firstCalculator.saveState();
    assertNotNull(savedStateOfFirst);

    // give the second some input
    secondCalculator.insertDigit(2);
    secondCalculator.insertPlus();
    secondCalculator.insertDigit(3);

    // save current state of second
    Serializable savedStateOfSecond = secondCalculator.saveState();
    assertNotNull(savedStateOfSecond);

    // load the saved state of the first to the second, and make sure state was loaded correctly
    secondCalculator.loadState(savedStateOfFirst);
    assertEquals("100", secondCalculator.output());
  }

  @Test
  public void when_there_is_no_equals_yet(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();

    // give some input
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertDigit(4);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(7);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(8);
    calculatorUnderTest.insertDigit(3);
    calculatorUnderTest.insertPlus();

    assertEquals("14-5+7-83+", calculatorUnderTest.output());
  }

  @Test
  public void input_has_multiple_orders(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();

    // give some input
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(7);

    assertEquals("15-7", calculatorUnderTest.output());
  }

  @Test
  public void input_has_multiple_orders_extreme(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();

    // give some input
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(1);

    assertEquals("5-1", calculatorUnderTest.output());
  }

  @Test
  public void when_calling_deleteLast_and_there_is_no_input_yet(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.deleteLast();
    assertEquals("0", calculatorUnderTest.output());
  }

  @Test
  public void calling_enough_times_to_deleteLast_that_all_input_is_deleted(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();

    // give some input
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.insertDigit(3);
    calculatorUnderTest.insertDigit(4);
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertDigit(6);
    calculatorUnderTest.insertDigit(7);

    // delete lasts
    calculatorUnderTest.deleteLast(); // 7
    calculatorUnderTest.deleteLast(); // 6
    calculatorUnderTest.deleteLast(); // 5
    calculatorUnderTest.deleteLast(); // 4
    calculatorUnderTest.deleteLast(); // 3
    calculatorUnderTest.deleteLast(); // 2
    calculatorUnderTest.deleteLast(); // 1

    assertEquals("0", calculatorUnderTest.output());
  }
}
