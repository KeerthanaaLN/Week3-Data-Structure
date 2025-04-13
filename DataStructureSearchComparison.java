import java.util.*;

public class DataStructureSearchComparison {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter dataset size: ");
        int n = in.nextInt();

        int[] array = new int[n];
        HashSet<Integer> hashSet = new HashSet<>();
        TreeSet<Integer> treeSet = new TreeSet<>();

        for (int i = 0; i < n; i++) {
            array[i] = i;
            hashSet.add(i);
            treeSet.add(i);
        }

        System.out.print("Enter target to search: ");
        int target = in.nextInt();

        long start, end;

        start = System.nanoTime();
        boolean foundArray = false;
        for (int value : array) {
            if (value == target) {
                foundArray = true;
                break;
            }
        }
        end = System.nanoTime();
        long arrayTime = end - start;

        start = System.nanoTime();
        boolean foundHashSet = hashSet.contains(target);
        end = System.nanoTime();
        long hashSetTime = end - start;

        start = System.nanoTime();
        boolean foundTreeSet = treeSet.contains(target);
        end = System.nanoTime();
        long treeSetTime = end - start;

        System.out.println("Array Search: " + arrayTime / 1000000.0 + "ms, Found: " + foundArray);
        System.out.println("HashSet Search: " + hashSetTime / 1000000.0 + "ms, Found: " + foundHashSet);
        System.out.println("TreeSet Search: " + treeSetTime / 1000000.0 + "ms, Found: " + foundTreeSet);

        in.close();
    }
}
