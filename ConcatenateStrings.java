public class ConcatenateStrings {
    public static String concatenate(String[] strings) {
        StringBuffer sb = new StringBuffer();
        for (String str : strings) {
            sb.append(str);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String[] strings = {"Java", " ", "is", " ", "cool!"};
        String result = concatenate(strings);
        System.out.println("Concatenated: " + result);
    }
}
