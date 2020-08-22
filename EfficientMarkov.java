import java.util.*;

/**
 * This version uses HashMaps to scan through
 * the training test. This makes generating N characters
 * an O(T+N) task where T is the size of the training text
 *
 * @author Sara Lemus
 *
 */


public class EfficientMarkov extends BaseMarkov {
	private Map<String,ArrayList<String>> myMap;
	/**
	 * Construct an EfficientMarkov object with
	 * the specified order
	 * @param order size of this markov generator
	 */
	public EfficientMarkov(int order) {
		super(order);
		myMap = new HashMap<>();
	}

	/**
	 * Default constructor has order 3
	 */
	public EfficientMarkov() {
		this(3);
	}

	/**
	 * Initializes a HashMap with substrings of the text as the key,
	 * and the value as an ArrayList of characters that follow the
	 * given substring
	 * @param text contains text from a specified file
	 */
	@Override
	public void setTraining(String text) {
		super.setTraining(text);
		myMap.clear();

		for (int i = 0; i < myText.length(); i++) {
			if(i+myOrder > text.length()){
				break;
			}
			String gram = myText.substring(i, i+myOrder);
			String next;
			if(i+myOrder+1 > myText.length()){
				next = PSEUDO_EOS;
			}
			else next = myText.substring(i+myOrder, i+myOrder+1);
			myMap.putIfAbsent(gram, new ArrayList<>());
			myMap.get(gram).add(next);
		}

	}

	/**
	 * A method that returns an ArrayList of all of the
	 * String characters that follow a specified substring in
	 * the given text
	 * @param key a substring of length myOrder from the text
	 * @return an String ArrayList of Strings of length 1
	 */
	@Override
	public ArrayList<String> getFollows(String key) {
		if(myMap.containsKey(key)) {
			return myMap.get(key);
		}
		else throw new NoSuchElementException(key+" not in map");

	}



}	
