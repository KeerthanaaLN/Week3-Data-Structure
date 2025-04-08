import java.util.*;

public class ZeroSumSubarrays {

    public static void findZeroSumSubarrays(int[] arr, int n) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int sum = 0;

        map.put(0, new ArrayList<>());
        map.get(0).add(-1);

        for (int i = 0; i < n; i++) {
            sum += arr[i];

            if (map.containsKey(sum)) {
                for (int start : map.get(sum)) {
                    System.out.println("Subarray found from index " + (start + 1) + " to " + i);
                }
            }

            map.putIfAbsent(sum, new ArrayList<>());
            map.get(sum).add(i);
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter size of array: ");
        int n = in.nextInt();

        int[] arr = new int[n];

        System.out.println("Enter " + n + " elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        findZeroSumSubarrays(arr, n);

        in.close();
    }
}
