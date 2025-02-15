import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Calculator {

  public static void main(String args[]) {

    try (BufferedReader biStream = new BufferedReader(new InputStreamReader(System.in))) {

      System.out.println("Please enter your equation let it be simple please no advance maths we stop before brackets");
      String userInput = biStream.readLine();

      System.out.println(calculateSolution(userInput));

    } catch (IOException e) {
      System.out.println("Unable to read user input");
    }

  }

  private static int calculateSolution(String input) {
    MyNotation convertedToPostFixNotation = convertToMyNotation(input);
    int calculatedResult = calculateMyNotation(convertedToPostFixNotation);
    return calculatedResult;
  }

  private static MyNotation convertToMyNotation(String input) {
    final MyNotation notation = new MyNotation();
    if (input != null && !input.isBlank()) {
      String str = "";
      for (int i = 0; i < input.length(); i++) {
        char s = input.charAt(i);
        if (s == ' ') {
          continue;
        }
        if (Character.isDigit(s)) {
          str = str + s;
        } else {
          notation.addOperand(Integer.valueOf(str));
          notation.addOperator(String.valueOf(s));
          str = "";
        }
      }
      notation.addOperand(Integer.valueOf(str));
    }

    return notation;
  }

  private static int calculateMyNotation(MyNotation notation) {
    Integer result = null;
    List<Integer> operands = notation.getOperands();
    List<String> operators = notation.getOpertors();

    for (int i = 0; i < operands.size(); i++) {
      if (result == null) {
        result = operands.get(i);
        continue;
      }

      String operator = operators.get(0);
      operators.remove(0);

      result = switch (operator) {
        case "+" -> result + operands.get(i);
        case "-" -> result - operands.get(i);
        case "*" -> result * operands.get(i);
        case "/" -> result / operands.get(i);
        default -> result;
      };
    }

    return result;
  }

  static class MyNotation {
    private List<Integer> operands = new ArrayList<>();
    private List<String> operators = new ArrayList<>();

    public List<Integer> getOperands() {
      return this.operands;
    }

    public List<String> getOpertors() {
      return this.operators;
    }

    public void addOperand(Integer val) {
      this.operands.add(val);
    }

    public void addOperator(String val) {
      this.operators.add(val);
    }

    @Override
    public String toString() {
      return operands.toString() + operators.toString();
    }
  }
}
