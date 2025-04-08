import java.util.Stack;
import java.util.Scanner;

class QueueandStacks {
    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();

    public void enqueue(int x) {
        stack1.push(x);
    }

    public int dequeue() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        if (stack2.isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return stack2.pop();
    }

    public boolean isEmpty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }
}

public class QueueUsingStacks {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        QueueandStacks queue = new QueueandStacks();

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Enqueue");
            System.out.println("2. Dequeue");
            System.out.println("3. Check if queue is empty");
            System.out.println("4. Exit");
            int choice = in.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter the element to enqueue: ");
                    int element = in.nextInt();
                    queue.enqueue(element);
                    System.out.println("Enqueued " + element);
                    break;
                case 2:
                    try {
                        int dequeuedElement = queue.dequeue();
                        System.out.println("Dequeued element: " + dequeuedElement);
                    } catch (IllegalStateException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    if (queue.isEmpty()) {
                        System.out.println("Queue is empty.");
                    } else {
                        System.out.println("Queue is not empty.");
                    }
                    break;
                case 4:
                    System.out.println("Exiting...");
                    in.close();
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
}
