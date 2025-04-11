import java.io.*;

public class InputToFile {
    public static void readInputToFile(String filename) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        FileWriter fw = new FileWriter(filename);
        String input;
        System.out.println("Enter text (type 'exit' to finish):");
        while (!(input = br.readLine()).equalsIgnoreCase("exit")) {
            fw.write(input + "\n");
        }
        fw.close();
        br.close();
    }

    public static void main(String[] args) throws IOException {
        readInputToFile("output.txt"); 
    }
}
