import java.util.Scanner;
import java.util.Deque;
import java.util.LinkedList;

public class SlidingWindowMaximum {

    public static void printMaxInSlidingWindow(int[] arr, int n, int k) {
        Deque<Integer> deque = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            while (!deque.isEmpty() && deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }

            while (!deque.isEmpty() && arr[deque.peekLast()] < arr[i]) {
                deque.pollLast();
            }

            deque.offerLast(i);

            if (i >= k - 1) {
                System.out.print(arr[deque.peekFirst()] + " ");
            }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter size of array: ");
        int n = in.nextInt();

        int[] arr = new int[n];

        System.out.println("Enter " + n + " elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        System.out.print("Enter window size k: ");
        int k = in.nextInt();

        System.out.println("Maximum in each sliding window:");
        printMaxInSlidingWindow(arr, n, k);

        in.close();
    }
}
