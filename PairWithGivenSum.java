import java.util.*;

public class PairWithGivenSum {

    public static boolean hasPairWithSum(int[] arr, int target) {
        Set<Integer> set = new HashSet<>();

        for (int num : arr) {
            int complement = target - num;
            if (set.contains(complement)) {
                System.out.println("Pair found: (" + complement + ", " + num + ")");
                return true;
            }
            set.add(num);
        }

        return false;
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

        boolean result = hasPairWithSum(arr, target);

        if (!result) {
            System.out.println("No pair found with the given sum.");
        }

        in.close();
    }
}
