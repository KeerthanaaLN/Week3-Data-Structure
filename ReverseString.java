import java.util.*;
public class ReverseString {
    public static String reverse(String input) {
        StringBuilder sb = new StringBuilder(input);
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter a text: ");
        String input = in.nextLine();
        String reversed = reverse(input);
        System.out.println("Reversed: " + reversed);
    }
}
