/**
 * file: DriverDFA.java
 * author: Michael Pair
 * course: CMPT 440
 * assignment: Lab 2
 * due date: February 15, 2016
 * version: 1.0
 *
 * This file contains the driver for the DFA
 */

import java.util.Scanner;


public class driverDFA {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    String userInput = in.nextLine();
    ManWolf.process(userInput);
  }
}