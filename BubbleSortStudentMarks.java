import java.util.Scanner;

public class BubbleSortStudentMarks {

    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap (current element > the next)
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            // If no elements were swapped, the array is sorted
            if (!swapped) break;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter number of students: ");
        int n = in.nextInt();

        int[] marks = new int[n];
        System.out.println("Enter marks of " + n + " students:");
        for (int i = 0; i < n; i++) {
            marks[i] = in.nextInt();
        }

        bubbleSort(marks);

        System.out.println("Sorted marks:");
        for (int mark : marks) {
            System.out.print(mark + " ");
        }

        in.close();
    }
}
