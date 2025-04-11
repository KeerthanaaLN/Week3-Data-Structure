import java.util.*;
public class HeapSortJobApplicants {

    public static void heapSort(int[] arr) {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, i, 0);
        }
    }

    private static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Recursively heapify the affected subtree
            heapify(arr, n, largest);
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter number of applicants: ");
        int n = in.nextInt();

        int[] salaryDemands = new int[n];
        System.out.println("Enter the salary demands of " + n + " applicants:");
        for (int i = 0; i < n; i++) {
            salaryDemands[i] = in.nextInt();
        }

        heapSort(salaryDemands);

        System.out.println("Sorted Salary Demands:");
        for (int salary : salaryDemands) {
            System.out.print(salary + " ");
        }

        in.close();
    }
}
