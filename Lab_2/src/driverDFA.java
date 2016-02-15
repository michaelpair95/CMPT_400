/**
 * Created by michaelpair on 2/12/16.
 */

import java.util.Scanner;


public class driverDFA {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    String userInput = in.nextLine();
    ManWolf.process(userInput);
  }
}