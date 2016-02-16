/**
 * Created by michaelpair on 2/12/16.
 */
public class ManWolf {
  private static final int q0 = 0;
  private static final int q1 = 1;
  private static final int q2 = 2;
  private static final int q3 = 3;
  private static final int q4 = 4;
  private static final int q5 = 5; // error
  private static final int q6 = 6;
  private static final int q7 = 7;
  private static final int q8 = 8;
  private static final int q9 = 9;
  private static final int q10 = 10;

  private static int state = q0;
  //private static int loc = 10;

  static private int[][] delta =
      { // w   g   c   n
          {q5, q1, q5, q5},
          {q5, q0, q5, q2},
          {q3, q5, q4, q1},
          {q2, q6, q5, q5},
          {q5, q7, q2, q5},
          {q5, q5, q5, q5}, // error state
          {q5, q3, q8, q5},
          {q8, q4, q5, q5},
          {q7, q5, q6, q9},
          {q5, q10, q5, q8},
          {q5, q9, q5, q5}
      };

  public static void process(String in) {
    int loc=4; // 4 is used just as an initialization
    for (int i = 0; i < in.length(); i++) {

      char c = in.charAt(i);

      switch (c) {

        case 'w':
          loc = 0;
          break;

        case 'g':
          loc = 1;
          break;

        case 'c':
          loc = 2;
          break;

        case 'n':
          loc = 3;
          break;

        default:
          state = q10;
          break;
      }

      try {
        state = delta[state][loc];
      } catch (ArrayIndexOutOfBoundsException ex) {
        state = delta[state][10];
      }
    }

    if (state == q10 ) {
      System.out.println("This is a Solution");
    } else if (state == q5 || state != q10) {
      System.out.println("This is not a solution");
    }

  }

}
