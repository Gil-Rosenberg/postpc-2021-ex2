                        -------------------------
                        ex3 2021 - calculator app
                        -------------------------
                        
QUESTION:
--------
Saying we want to add a cool feature - button "x" to run multiplication.
What code do we need to change/add/remove to support this feature?
Which tests can we run on the calculator? On the activity? On the app?

ANSWER:
-------
The files in which we will need to update the code: 
1. SimpleCalculatorImpl - add a new function multiplication(int x, int y).
2. MainActivity - add a setOnClickListener for button "x".

Test can we run on the calculator:
-   Checking the logical correctness of a multiplication operation.
    For example: "7x2=" -> 14.
    
Test can we run on the activity:
-   when user clicks buttonX then activity should forward call to calculator and show the expected
    calculator output right away.
    
Test can we run on the app:
-   run clicks on "7X5<backspace>4=" and expect to get "28" as the answer.

STATEMENT
---------
I pledge the highest level of ethical principles in support of academic excellence.  
I ensure that all of my work reflects my own abilities and not those of someone else.

Gil Rosenberg
