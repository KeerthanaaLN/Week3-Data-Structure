public class FirstNegative {
    public static int findFirstNegative(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 0) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {5, 10, 3, 0, -7, 4, -2};

        int index = findFirstNegative(arr);

        if (index != -1) {
            System.out.println("First negative number found at index: " + index + ", Value: " + arr[index]);
        } else {
            System.out.println("No negative number found in the array.");
        }
    }
}
