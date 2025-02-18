public class Pattern {
  public static void main(String args[]) {
    int totalRows = 5;
    int starLength = 1;

    for (int i = totalRows; i > 0; i--) {
      for (int ii = 0; ii < i - 1; ii++) {
        System.out.print(" ");
      }
      for (int ii = 0; ii < starLength; ii++) {
        System.out.print("*");
      }

      starLength += 2;
      System.out.println();
    }
  }
}
