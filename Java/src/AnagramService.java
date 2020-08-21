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
        
        //Storig the langth of charactors
        List<Integer> wordLength = new ArrayList<>(); 
        
        //Storing the the lemgth(count) of anagram per charactor
        List<Integer> lengthofanagrams = new ArrayList<>();
         

            for (String word : words) {
                
                //Adds the sum of charactor of word
                wordLength.add(word.length());
                
                //Calls from permutation function below.
                ArrayList<String> permutations = permute(word);
                List<String> anagrams = new ArrayList<String>();
                    
                    //Loop through all the permtation,
                    //and adds all permutations in a list
                    for (String x : permutations) {
                        
                            if (words.contains(x) && !word.equals(x)) {
                                    anagrams.add(x);
                            }
                    }
                   
                    //Adding the number of anagrams found
                    //in the file for each word to a list
                    lengthofanagrams.add(anagrams.size());   

            }   

                for(int i: wordLength){

                    //create an instance of a class(AnagramCounter)
                    //per each word
                    makeAnagram.add(new AnagramCounter(i, lengthofanagrams.size()));
                }
            
            //returns each instance of class (AnagramCounter)
            return makeAnagram;
    }

        /**  *Permutation function.
         *The function will reorder the letters,
         *and return all possible set of permutations per word
	 * Will create a random permutation of given string
	 * 
	 * @param inputword
	 * @return permuted string
	 */
    private static ArrayList<String> permute(String inputword) {
        ArrayList<String> permutations = new ArrayList<String>();
                   
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
