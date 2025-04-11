public class CompareBufferBuilder {
    public static void compare() {
        int count = 1000000;
        long start, end;

        start = System.nanoTime();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append("hello");
        }
        end = System.nanoTime();
        System.out.println("StringBuilder time: " + (end - start) + " ns");

        start = System.nanoTime();
        StringBuffer sbf = new StringBuffer();
        for (int i = 0; i < count; i++) {
            sbf.append("hello");
        }
        end = System.nanoTime();
        System.out.println("StringBuffer time: " + (end - start) + " ns");
    }

    public static void main(String[] args) {
        compare();
    }
}
