/*
 * File: HangmanLexicon.java
 * -------------------------
 * This is a stub file containing the HangmanLexicon
 * class so that you can implement the first part
 */
public class HangmanLexicon {
	private String[] words;
	/*
	 * sets the initial word list
	 */
	public HangmanLexicon() {
		words = new String[]{"BODY",
				 		     "COMPUTER",
				             "CONNOISSEUR",
						     "DEHYDRATE",
						     "FUZZY",
						     "HAPPY",
						     "DYNAMITE",
						     "EXCITEMENT",
						     "TECHNOLOGY",
						     "CONFIDENCE",
						     "LOVELY"};
	}
	/*
	 * Returns the number of words in the lexicon
	 */
	public int getWordCount() {
		return words.length;
	}
	/*
	 * Returns the word at the specified index
	 */
	public String getWord(int index) {
		return words[index];
	}
	
}
