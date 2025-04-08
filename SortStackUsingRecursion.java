import java.util.Stack;
import java.util.Scanner;

public class SortStackUsingRecursion {

    public static void sortStack(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }

        int topElement = stack.pop();
        sortStack(stack);
        insertSorted(stack, topElement);
    }

    private static void insertSorted(Stack<Integer> stack, int element) {
        if (stack.isEmpty() || stack.peek() <= element) {
            stack.push(element);
            return;
        }

        int top = stack.pop();
        insertSorted(stack, element);
        stack.push(top);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Stack<Integer> stack = new Stack<>();

        System.out.print("Enter the number of elements: ");
        int n = in.nextInt();

        System.out.println("Enter " + n + " elements:");
        for (int i = 0; i < n; i++) {
            stack.push(in.nextInt());
        }

        System.out.println("Original Stack: " + stack);
        sortStack(stack);
        System.out.println("Sorted Stack: " + stack);

        in.close();
    }
}
