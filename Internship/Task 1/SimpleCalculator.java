import java.util.Scanner;

public class SimpleCalculator {

    public class Calculator {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            String operator;

            do {
                System.out.println("Simple Calculator");
                System.out.println("Operations: +, -, *, /, sqrt, ^");

                System.out.print("Enter first number: ");
                double num1 = scanner.nextDouble();

                System.out.print("Enter operation: ");
                operator = scanner.next();

                if (operator.equals("sqrt")) {
                    double result = calculateSquareRoot(num1);
                    System.out.println("Result: " + result);
                } else if (operator.equals("^")) {
                    System.out.print("Enter second number: ");
                    double num2 = scanner.nextDouble();
                    double result = calculateExponentiation(num1, num2);
                    System.out.println("Result: " + result);
                } else {
                    System.out.print("Enter second number: ");
                    double num2 = scanner.nextDouble();

                    double result = performOperation(num1, num2, operator);

                    if (Double.isNaN(result)) {
                        System.out.println("Error: Cannot divide by zero.");
                    } else {
                        System.out.println("Result: " + result);
                    }
                }

                System.out.print("Do you want to perform another operation? (y/n): ");
                char choice = scanner.next().charAt(0);

                if (choice != 'y' && choice != 'Y') {
                    System.out.println("Exiting calculator.");
                }

            } while (!operator.equals("sqrt") && !operator.equals("^"));

            scanner.close();
        }

        private static double performOperation(double num1, double num2, String operator) {
            switch (operator) {
                case "+":
                    return num1 + num2;
                case "-":
                    return num1 - num2;
                case "*":
                    return num1 * num2;
                case "/":
                    if (num2 != 0) {
                        return num1 / num2;
                    } else {
                        return Double.NaN; // Indicate division by zero
                    }
                default:
                    System.out.println("Invalid operation. Please enter a valid operation.");
                    return Double.NaN;
            }
        }

        private static double calculateSquareRoot(double num) {
            if (num >= 0) {
                return Math.sqrt(num);
            } else {
                System.out.println("Error: Cannot calculate square root of a negative number.");
                return Double.NaN;
            }
        }

        private static double calculateExponentiation(double base, double exponent) {
            return Math.pow(base, exponent);
        }
    }
}
