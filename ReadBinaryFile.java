import java.io.*;

public class ReadBinaryFile {
    public static void readBinaryFile(String filename) throws IOException {
        FileInputStream fis = new FileInputStream(filename);
        InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        br.close();
        isr.close();
    }

    public static void main(String[] args) throws IOException {
        readBinaryFile("example.txt");
    }
}
