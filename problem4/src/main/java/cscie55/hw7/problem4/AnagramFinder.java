package cscie55.hw7.problem4;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Logger;

public class AnagramFinder {

    public static Map<String, String> anagramMap = new HashMap<>();
    public static Map<String, List<String>> anagramWords = new HashMap<>();
    public static Logger logger = Logger.getLogger(AnagramFinder.class.getName());

    public static void populateMap(String line){
        String groomed = line.trim();
        groomed = line.replaceAll("[^a-zA-Z]", "");
        char [] letters = line.toCharArray();
        Arrays.sort(letters);
        String sorted = new String(letters);
        anagramMap.put(sorted, line);

        if(anagramWords.get(sorted) == null || anagramWords.get(sorted).size() == 0){
            List<String> words = new ArrayList<>();
            words.add(line);
            anagramWords.put(sorted, words);
        } else {
            List<String> words = anagramWords.get(sorted);
            words.add(line);
            anagramWords.replace(sorted, words);
        }
    }

    public static void main(String [] args){
        Path path = Paths.get(System.getProperty("user.dir"), "problem4", "anagram-data.txt");
        File file = new File(path.toString());
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String line = null;
            while((line = bufferedReader.readLine()) != null){
                populateMap(line);
            }
        } catch (IOException e) {
           logger.warning("Unable to read file");
        }

        outputAnagrams();
    }

    private static void outputAnagrams() {
        for(String key : anagramWords.keySet()){
            if(anagramWords.get(key).size() > 1){
                System.out.print(key + " -> ");
                for(String word : anagramWords.get(key)){
                    System.out.print(word + " ");
                }
                System.out.println();
            }
        }
    }

}
