import java.util.Scanner;

public class QuickSortProductPrices {

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // get pivot index
            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];  
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                // Swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Swap arr[i + 1] and pivot 
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter number of products: ");
        int n = in.nextInt();

        int[] productPrices = new int[n];
        System.out.println("Enter the prices of " + n + " products:");
        for (int i = 0; i < n; i++) {
            productPrices[i] = in.nextInt();
        }

        quickSort(productPrices, 0, productPrices.length - 1);

        System.out.println("Sorted Product Prices:");
        for (int price : productPrices) {
            System.out.print(price + " ");
        }

        in.close();
    }
}
