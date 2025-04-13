import java.util.Arrays;
import java.util.Random;

public class SortingAlgorithmComparison {

    public static void main(String[] args) {
        int[] datasetSizes = {1000, 10000, 1000000};
        Random rand = new Random();

        for (int size : datasetSizes) {
            int[] data = new int[size];
            for (int i = 0; i < size; i++) {
                data[i] = rand.nextInt(size);
            }

            int[] dataForMergeSort = Arrays.copyOf(data, data.length);
            int[] dataForQuickSort = Arrays.copyOf(data, data.length);

            long startTime = System.nanoTime();
            bubbleSort(data);
            long endTime = System.nanoTime();
            long bubbleSortTime = endTime - startTime;

            startTime = System.nanoTime();
            mergeSort(dataForMergeSort, 0, dataForMergeSort.length - 1);
            endTime = System.nanoTime();
            long mergeSortTime = endTime - startTime;

            startTime = System.nanoTime();
            quickSort(dataForQuickSort, 0, dataForQuickSort.length - 1);
            endTime = System.nanoTime();
            long quickSortTime = endTime - startTime;

            System.out.println("Dataset Size: " + size);
            System.out.println("Bubble Sort Time: " + bubbleSortTime / 1000000.0 + "ms");
            System.out.println("Merge Sort Time: " + mergeSortTime / 1000000.0 + "ms");
            System.out.println("Quick Sort Time: " + quickSortTime / 1000000.0 + "ms");
            System.out.println();
        }
    }

    public static void bubbleSort(int[] data) {
        for (int i = 0; i < data.length - 1; i++) {
            for (int j = 0; j < data.length - 1 - i; j++) {
                if (data[j] > data[j + 1]) {
                    int temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                }
            }
        }
    }

    public static void mergeSort(int[] data, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(data, left, mid);
            mergeSort(data, mid + 1, right);
            merge(data, left, mid, right);
        }
    }

    private static void merge(int[] data, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        System.arraycopy(data, left, leftArray, 0, n1);
        System.arraycopy(data, mid + 1, rightArray, 0, n2);

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                data[k] = leftArray[i];
                i++;
            } else {
                data[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            data[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < n2) {
            data[k] = rightArray[j];
            j++;
            k++;
        }
    }

    public static void quickSort(int[] data, int low, int high) {
        if (low < high) {
            int pivot = partition(data, low, high);
            quickSort(data, low, pivot - 1);
            quickSort(data, pivot + 1, high);
        }
    }

    private static int partition(int[] data, int low, int high) {
        int pivot = data[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (data[j] < pivot) {
                i++;
                int temp = data[i];
                data[i] = data[j];
                data[j] = temp;
            }
        }

        int temp = data[i + 1];
        data[i + 1] = data[high];
        data[high] = temp;

        return i + 1;
    }
}
