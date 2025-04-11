import java.io.*;

public class ReadFile {
    public static void readFile(String filename) throws IOException {
        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        br.close();
        fr.close();
    }

    public static void main(String[] args) throws IOException {
        readFile("example.txt"); 
    }
}
