public class StringConcatenationPerformance {

    public static void main(String[] args) {
        int[] operationCounts = {1000, 10000, 1000000};

        for (int n : operationCounts) {
            long startTime, endTime;

            startTime = System.nanoTime();
            stringConcatenation(n);
            endTime = System.nanoTime();
            long stringTime = endTime - startTime;

            startTime = System.nanoTime();
            stringBuilderConcatenation(n);
            endTime = System.nanoTime();
            long stringBuilderTime = endTime - startTime;

            startTime = System.nanoTime();
            stringBufferConcatenation(n);
            endTime = System.nanoTime();
            long stringBufferTime = endTime - startTime;

            System.out.println("Operations Count: " + n);
            System.out.println("String Time: " + stringTime / 1000000.0 + "ms");
            System.out.println("StringBuilder Time: " + stringBuilderTime / 1000000.0 + "ms");
            System.out.println("StringBuffer Time: " + stringBufferTime / 1000000.0 + "ms");
            System.out.println();
        }
    }

    public static void stringConcatenation(int n) {
        String result = "";
        for (int i = 0; i < n; i++) {
            result += "Hello ";
        }
    }

    public static void stringBuilderConcatenation(int n) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < n; i++) {
            result.append("Hello ");
        }
    }

    public static void stringBufferConcatenation(int n) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < n; i++) {
            result.append("Hello ");
        }
    }
}
