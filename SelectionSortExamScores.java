import java.util.Scanner;

public class SelectionSortExamScores {

    public static void selectionSort(int[] arr) {
        int n = arr.length;

        // Traverse 
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            // Find the min number in the unsorted part of the array
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            // Swap the found min number with the first unsorted number
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter number of students: ");
        int n = in.nextInt();

        int[] examScores = new int[n];
        System.out.println("Enter the exam scores of " + n + " students:");
        for (int i = 0; i < n; i++) {
            examScores[i] = in.nextInt();
        }

        selectionSort(examScores);

        System.out.println("Sorted Exam Scores:");
        for (int score : examScores) {
            System.out.print(score + " ");
        }

        in.close();
    }
}
