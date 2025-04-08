import java.util.*;

public class TwoSumProblem {

    public static int[] twoSum(int[] arr, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            int complement = target - arr[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(arr[i], i);
        }

        return new int[] { -1, -1 };
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

        System.out.print("Enter target sum: ");
        int target = in.nextInt();

        int[] result = twoSum(arr, target);

        if (result[0] == -1) {
            System.out.println("No pair found with the given target.");
        } else {
            System.out.println("Pair found at indices: " + result[0] + " and " + result[1]);
        }

        in.close();
    }
}
