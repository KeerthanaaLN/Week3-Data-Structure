import java.util.Scanner;

public class CountingSortStudentAges {

    public static void countingSort(int[] arr) {
        int maxAge = 18;
        int minAge = 10;

        int[] count = new int[maxAge - minAge + 1];

        for (int i = 0; i < arr.length; i++) {
            count[arr[i] - minAge]++;
        }

        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        int[] output = new int[arr.length];

        for (int i = arr.length - 1; i >= 0; i--) {
            output[count[arr[i] - minAge] - 1] = arr[i];
            count[arr[i] - minAge]--;
        }

        System.arraycopy(output, 0, arr, 0, arr.length);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter number of students: ");
        int n = in.nextInt();

        int[] studentAges = new int[n];
        System.out.println("Enter the ages of " + n + " students (ranging from 10 to 18):");
        for (int i = 0; i < n; i++) {
            studentAges[i] = in.nextInt();
        }

        countingSort(studentAges);

        System.out.println("Sorted Student Ages:");
        for (int age : studentAges) {
            System.out.print(age + " ");
        }

        in.close();
    }
}
