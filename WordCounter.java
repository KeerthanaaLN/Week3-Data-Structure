import java.io.*;

public class WordCounter {
    public static int countWord(String filename, String word) throws IOException {
        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);
        int count = 0;
        String line;
        while ((line = br.readLine()) != null) {
            String[] words = line.split("\\s+");
            for (String w : words) {
                if (w.equals(word)) count++;
            }
        }
        br.close();
        fr.close();
        return count;
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Count: " + countWord("example.txt", "Flowers")); // Replace with your file and word
    }
}
