import java.util.Arrays;
import java.util.Random;

public class SearchPerformanceComparison {

    public static void main(String[] args) {
        int[] datasetSizes = {1000, 10000, 1000000};
        Random rand = new Random();

        for (int size : datasetSizes) {
            int[] data = new int[size];
            for (int i = 0; i < size; i++) {
                data[i] = rand.nextInt(size);
            }

            int target = rand.nextInt(size);

            long startTime = System.nanoTime();
            linearSearch(data, target);
            long endTime = System.nanoTime();
            long linearSearchTime = endTime - startTime;

            Arrays.sort(data);
            startTime = System.nanoTime();
            binarySearch(data, target);
            endTime = System.nanoTime();
            long binarySearchTime = endTime - startTime;

            System.out.println("Dataset Size: " + size);
            System.out.println("Linear Search Time: " + linearSearchTime / 1000000.0 + "ms");
            System.out.println("Binary Search Time: " + binarySearchTime / 1000000.0 + "ms");
            System.out.println();
        }
    }

    public static int linearSearch(int[] data, int target) {
        for (int i = 0; i < data.length; i++) {
            if (data[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static int binarySearch(int[] data, int target) {
        int left = 0;
        int right = data.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (data[mid] == target) {
                return mid;
            }
            if (data[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
