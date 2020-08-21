import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnagramService {
    

    public List<AnagramCounter> compute(String dictionaryLocation) throws IOException {
        List<String> words = FileUtils.readLines(new File(dictionaryLocation));
        
        List<AnagramCounter> makeAnagram = new ArrayList<>();
        List<Integer> wordLength = new ArrayList<>(); //Storig the langth of charactors
        List<Integer> lengthofanagrams = new ArrayList<>(); //Storing the the lemgth(count) of anagram per charactor
         

            for (String word : words) {

                wordLength.add(word.length());
                
                //Calls from permutation function below.
                ArrayList<String> permutations = permute(word);
                List<String> anagrams = new ArrayList<String>();
                
                    for (String x : permutations) {
                        
                            if (words.contains(x) && !word.equals(x)) {
                                    anagrams.add(x);
                            }
                    }
                    //System.out.println(anagrams.size());
                    lengthofanagrams.add(anagrams.size());   

            }   

                for(int i: wordLength){

                    makeAnagram.add(new AnagramCounter(i, lengthofanagrams.size()));
                }

            return makeAnagram;
    }

        //Permutation function.
        //
    private static ArrayList<String> permute(String inputword) {
        ArrayList<String> permutations = new ArrayList<String>();
                    // base case
            if (inputword.equals("")) {
                 ArrayList<String> empty = new ArrayList<String>();
                 empty.add("");
                 return empty;
            }

            for (int i = 0; i < inputword.length(); i++) {
                char randChar = inputword.charAt(i);
                String remaining = inputword.substring(0, i)
                        + inputword.substring(i + 1);
                
                 ArrayList<String> permuted = permute(remaining);
                 
                for (String x : permuted) {
                    x = x + randChar;
                    permutations.add(x);
                }
            }
            
    return permutations;

    }
}
