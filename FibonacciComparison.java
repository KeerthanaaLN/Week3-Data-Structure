import java.util.Scanner;

public class FibonacciComparison {

    public static int fibonacciRecursive(int n) {
        if (n <= 1) return n;
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    public static int fibonacciIterative(int n) {
        if (n <= 1) return n;
        int a = 0, b = 1, sum = 0;
        for (int i = 2; i <= n; i++) {
            sum = a + b;
            a = b;
            b = sum;
        }
        return b;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter N: ");
        int n = in.nextInt();

        long start, end;

        start = System.nanoTime();
        int recursiveResult = fibonacciRecursive(n);
        end = System.nanoTime();
        long recursiveTime = end - start;

        start = System.nanoTime();
        int iterativeResult = fibonacciIterative(n);
        end = System.nanoTime();
        long iterativeTime = end - start;

        System.out.println("Recursive Result: " + recursiveResult);
        System.out.println("Recursive Time: " + recursiveTime / 1000000.0 + "ms");
        System.out.println("Iterative Result: " + iterativeResult);
        System.out.println("Iterative Time: " + iterativeTime / 1000000.0 + "ms");

        in.close();
    }
}
