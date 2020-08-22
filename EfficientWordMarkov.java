import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Similar to EfficientMarkov, but uses WordGram objects instead of String
 * objects to generate random text.
 *
 * @author Sara Lemus
 *
 */

public class EfficientWordMarkov extends BaseWordMarkov {
    private Map<WordGram, ArrayList<String>> myMap;
    /**
     * Construct an EfficientWordMarkov object with
     * the specified order
     * @param order size of this markov generator
     */
    public EfficientWordMarkov(int order) {
        super(order);
        myMap = new HashMap<>();
    }

    /**
     * Default constructor has order 2
     */
    public EfficientWordMarkov() {
        this(2);
    }

    /**
     * A method that scans text and creates WordGram objects.
     * Initializes a HashMap with these WordGram objects as the key,
     * and the value as an ArrayList of words that follow the
     * given WordGram
     * @param text contains text from a specified file
     */
    @Override
    public void setTraining(String text) {
        super.setTraining(text);
        myWords = text.split("\\s+");
        myMap.clear();

        for (int i = 0; i < myWords.length; i++) {
            if(i+myOrder > myWords.length){
                break;
            }
            WordGram key = new WordGram(myWords, i, myOrder);
            String value;
            if(i+myOrder == myWords.length){
                value = PSEUDO_EOS;
            }
            else value = myWords[i+myOrder];
            myMap.putIfAbsent(key, new ArrayList<>());
            myMap.get(key).add(value);
        }

    }

    /**
     * A method that returns an ArrayList of all of the
     * words that follow a specified WordGram in the given
     * text
     * @param kGram a WordGram from the text
     * @return an String ArrayList of words
     */
    @Override
    public ArrayList<String> getFollows(WordGram kGram) {
        if(myMap.containsKey(kGram)) {
            return myMap.get(kGram);
        }
        else throw new NoSuchElementException(kGram+ " not in map");

    }
}
