import java.util.HashSet;
import java.util.*;

public class RemoveDuplicates {
    public static String removeDuplicates(String input) {
        StringBuilder sb = new StringBuilder();
        HashSet<Character> seen = new HashSet<>();
        for (char c : input.toCharArray()) {
            if (!seen.contains(c)) {
                sb.append(c);
                seen.add(c);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
		System.out.print("Enter a text: ");
        String input = in.nextLine();
        String result = removeDuplicates(input);
        System.out.println("Without Duplicates: " + result);
    }
}
