import acm.util.*;
import java.util.*;

public class HangmanConsoleGame {
	private HangmanLexicon pWordList;
	private RandomGenerator pGenerator;
	private int pGames;
	
	public HangmanConsoleGame() {
		pWordList = new HangmanLexicon();
		pGenerator = new RandomGenerator();
		pGames = 1;
	}
	
	/*
	 * Returns whether you are satisfied with playing
	 * the Hangman game after completing pGames
	 */
	public boolean isFinished() {
		return pGames == 0;
	}
	/*
	 * Helper method for run()
	 * @ param playerGuess is a set of characters
	 * containing the letters in the word the player 
	 * already guesses
	 * @ param letter is the letter the player guesses
	 * @ param String is the word the player is trying to guess
	 * @ returns true if letter in word 
	 * prints to see if letter in the word and
	 * updates the set if the letter is in the word
	 */
	private boolean inWord(Set<Character> playerGuess, char letter, String word) {
		if(playerGuess.contains(letter)) {
			System.out.println("You already guessed this letter!");
			return true;
		}
		if(word.indexOf(letter)>-1) {
			System.out.println("That guess is correct.");
			playerGuess.add(letter);
			return true;
		}
		else {
			System.out.println("There are no "+letter+"'s in the word.");
			return false; 
		}
	}
	/*
	 * Helper method for run()
	 * returns a random word from the lexicon
	 */
	private String getRandomWord() {
		int numWords = pWordList.getWordCount();
		int randIndex = pGenerator.nextInt(0, numWords-1);
		return pWordList.getWord(randIndex);
	}
	/*
	 * Helper method for run()
	 * Prints out the letters in the word that the
	 * player has already guessed
	 * @ returns the number of letters left
	 */
	private int printCurrentWord(Set<Character> playerGuess, String word) {
		String out = "";
		int numLetters = 0;
		for(int i = 0; i < word.length(); i++) {
			char letter = word.charAt(i);
			if(playerGuess.contains(letter)) {
				out += letter;
			}
			else {
				out += "-";
				numLetters ++;
			}
		}
		System.out.println("You current word is "+ out);
		return numLetters;
	}
	/*
	 * Helper method for getPlayerLetter()
	 * Checks to see if the input is valid
	 */
	private boolean isInputValid(String input) {
		if(input.length()==1 && Character.isLetter(input.charAt(0))) {
			return true;
		}
		return false;
	}
	/*
	 * Helper methof for run()
	 * gets a valid input letter from the player
	 * @ param sc reads in the next line of input
	 * @ returns an uppercase valid char
	 */
	private char getValidLetter(Scanner sc) {
		System.out.print("Type in a letter to guess: ");
		String input = sc.nextLine();
		while(!isInputValid(input)) {
			System.out.print("Input not valid. Please type a letter to guess: ");
			input = sc.nextLine();
		}
		return input.toUpperCase().charAt(0);
	}
	/*
	 * Runs game 
	 */
	public void run() {
		System.out.println("Welcome to Hangman!");
		Scanner sc = new Scanner(System.in);
		
		while(!isFinished()) {
			String wordToGuess = getRandomWord();
	
			Set<Character> guesses = new HashSet<Character>();
			int numGuessesLeft = 8;
			int numLettersLeft = wordToGuess.length();
			
			printCurrentWord(guesses, wordToGuess);
			
			while(numGuessesLeft > 0 && numLettersLeft > 0) {
				System.out.println("You have " + numGuessesLeft + " guesses left.");
				char letterInput = getValidLetter(sc);
				boolean isInWord = inWord(guesses, letterInput, wordToGuess);
				if(!isInWord) {
					numGuessesLeft -= 1;
				}
				
				numLettersLeft = printCurrentWord(guesses, wordToGuess);
			}
			
			if(numLettersLeft==0) {
				System.out.println("You win!");
			}
			else {
				System.out.println("You lose.");
			}
			pGames -= 1;
		}
		sc.close();
		System.out.println("Thanks for playing Hangman! See you next time!");
	}
	
	

}
