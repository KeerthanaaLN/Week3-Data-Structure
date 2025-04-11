import java.util.Arrays;
import java.util.Scanner;

public class SearchChallenge1 {
    public static int findMissingPositive(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            while (arr[i] > 0 && arr[i] <= arr.length && arr[arr[i] - 1] != arr[i]) {
                int temp = arr[arr[i] - 1];
                arr[arr[i] - 1] = arr[i];
                arr[i] = temp;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != i + 1) {
                return i + 1;
            }
        }
        return arr.length + 1;
    }

    public static int binarySearch(int[] arr, int target) {
        Arrays.sort(arr);
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == target) return mid;
            else if (arr[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of elements in the array: ");
        int n = scanner.nextInt();
        int[] arr1 = new int[n];
        
        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < n; i++) {
            arr1[i] = scanner.nextInt();
        }

        int missing = findMissingPositive(arr1);
        System.out.println("Smallest missing positive number: " + missing);

        System.out.print("Enter the target number for binary search: ");
        int target = scanner.nextInt();

        int index = binarySearch(arr1, target);
        if (index != -1) {
            System.out.println("Element " + target + " found at index (after sort): " + index);
        } else {
            System.out.println("Element " + target + " not found.");
        }

        scanner.close();
    }
}
