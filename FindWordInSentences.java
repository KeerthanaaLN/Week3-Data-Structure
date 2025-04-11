public class FindWordInSentences {
    public static String searchWord(String[] sentences, String word) {
        for (String sentence : sentences) {
            if (sentence.contains(word)) {
                return sentence;
            }
        }
        return "Not Found";
    }

    public static void main(String[] args) {
        String[] sentences = {
            "The sun is shining today.",
            "Java is a versatile programming language.",
            "Let's go for a walk in the park.",
            "I love solving coding problems."
        };

        String wordToFind = "Java";
        String result = searchWord(sentences, wordToFind);

        System.out.println("Result: " + result);
    }
}
