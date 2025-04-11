import java.util.Scanner;

public class MergeSortBookPrices {

    public static void mergeSort(int[] arr) {
        if (arr.length < 2) {
            return;
        }
        
        int mid = arr.length / 2;
        int[] left = new int[mid];
        int[] right = new int[arr.length - mid];
        
        System.arraycopy(arr, 0, left, 0, mid);
        System.arraycopy(arr, mid, right, 0, arr.length - mid);

        mergeSort(left);
        mergeSort(right);
        merge(arr, left, right);
    }

    private static void merge(int[] arr, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;
        
        // Merge the 2 sorted halves
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }

        // Copy remaining elements - left
        while (i < left.length) {
            arr[k++] = left[i++];
        }

        // Copy remaining elements - right
        while (j < right.length) {
            arr[k++] = right[j++];
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter number of books: ");
        int n = in.nextInt();

        int[] bookPrices = new int[n];
        System.out.println("Enter prices of " + n + " books:");
        for (int i = 0; i < n; i++) {
            bookPrices[i] = in.nextInt();
        }

        mergeSort(bookPrices);

        System.out.println("Sorted Book Prices:");
        for (int price : bookPrices) {
            System.out.print(price + " ");
        }

        in.close();
    }
}
