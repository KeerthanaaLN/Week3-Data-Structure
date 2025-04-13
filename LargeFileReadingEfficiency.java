import java.io.*;

public class LargeFileReadingEfficiency {

    public static void main(String[] args) throws IOException {
        String filePath = "largefile.txt";

        long startTime, endTime;

        startTime = System.nanoTime();
        fileReaderMethod(filePath);
        endTime = System.nanoTime();
        long fileReaderTime = endTime - startTime;

        startTime = System.nanoTime();
        inputStreamReaderMethod(filePath);
        endTime = System.nanoTime();
        long inputStreamReaderTime = endTime - startTime;

        System.out.println("FileReader Time: " + fileReaderTime / 1000000.0 + "ms");
        System.out.println("InputStreamReader Time: " + inputStreamReaderTime / 1000000.0 + "ms");
    }

    public static void fileReaderMethod(String filePath) throws IOException {
        FileReader fileReader = new FileReader(filePath);
        int data;
        while ((data = fileReader.read()) != -1) {
        }
        fileReader.close();
    }

    public static void inputStreamReaderMethod(String filePath) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(filePath));
        int data;
        while ((data = inputStreamReader.read()) != -1) {
        }
        inputStreamReader.close();
    }
}
