import java.util.Scanner;

public class InsertionSortEmployeeIDs {

    public static void insertionSort(int[] arr) {
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;

            // Move elements of arr[0..i-1] that are greater than key to one position ahead
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter number of employees: ");
        int n = in.nextInt();

        int[] employeeIDs = new int[n];
        System.out.println("Enter employee IDs:");
        for (int i = 0; i < n; i++) {
            employeeIDs[i] = in.nextInt();
        }

        insertionSort(employeeIDs);

        System.out.println("Sorted Employee IDs:");
        for (int id : employeeIDs) {
            System.out.print(id + " ");
        }

        in.close();
    }
}
