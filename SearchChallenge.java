import java.io.*;
import java.util.*;

public class SearchChallenge {

    public static void main(String[] args) throws IOException {
        compareStringBuilderVsStringBuffer();
        compareFileReaderVsInputStreamReader("largefile.txt");
    }

    static void compareStringBuilderVsStringBuffer() {
        String word = "Hello";
        int times = 1_000_000;

        long startTime = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++) {
            sb.append(word);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("StringBuilder time: " + (endTime - startTime) + "ms");

        startTime = System.currentTimeMillis();
        StringBuffer sbf = new StringBuffer();
        for (int i = 0; i < times; i++) {
            sbf.append(word);
        }
        endTime = System.currentTimeMillis();
        System.out.println("StringBuffer time: " + (endTime - startTime) + "ms");
    }

    static void compareFileReaderVsInputStreamReader(String filePath) throws IOException {
        long startTime = System.currentTimeMillis();
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("File not found: " + filePath);
            return;
        }

        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        int wordCount = 0;
        while ((line = br.readLine()) != null) {
            wordCount += line.split("\\s+").length;
        }
        br.close();
        long endTime = System.currentTimeMillis();
        System.out.println("FileReader Word Count: " + wordCount);
        System.out.println("FileReader Time: " + (endTime - startTime) + "ms");

        startTime = System.currentTimeMillis();
        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis);
        br = new BufferedReader(isr);
        wordCount = 0;
        while ((line = br.readLine()) != null) {
            wordCount += line.split("\\s+").length;
        }
        br.close();
        endTime = System.currentTimeMillis();
        System.out.println("InputStreamReader Word Count: " + wordCount);
        System.out.println("InputStreamReader Time: " + (endTime - startTime) + "ms");
    }
}