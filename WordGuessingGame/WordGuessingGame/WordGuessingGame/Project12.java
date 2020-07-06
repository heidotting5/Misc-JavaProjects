/*
 A program that plays simple word guessing game. In this game the user provides a list of
 words to the program. The program randomly selects one of the words to be guessed from
 this list. The player then guesses letters in an attempt to figure out what the hidden
 word might be. The number of guesses that the user takes are tracked and reported at the
 end of the game.

 @author Jared Heidotting

*/
package WordGuessingGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Project12 {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter a filename containing your wordlist: ");
		File file = new File(scanner.next());
		Scanner fileScanner = new Scanner(file);
		Scanner inScanner = new Scanner(System.in);
		ArrayList<String> list = new ArrayList<>();
		ArrayList<String> historyList = new ArrayList<>();

		list = getList(fileScanner);
		System.out.println("Read " + list.size() + " words from the file\n");

		while (true) {
			String solution = getRandomWord(list);
			String currentGuess = starWord(solution);
			char guessChar = ' ';
			boolean flag = true;
			while (flag) {
				System.out.println("\nThe word to guess is: " + modifyGuess(guessChar, solution, currentGuess));
				System.out.println("Previous characters guessed: " + historyList.toString());

				guessChar = getCharacterGuess(new Scanner(System.in));
				historyList.add(Character.toString(guessChar));

				ArrayList<Character> charList = new ArrayList<Character>();
				for (char c : solution.toCharArray()) {
					charList.add(c);
				}
				if (checkInList(guessChar, charList)) {
					currentGuess = modifyGuess(guessChar, solution, currentGuess);
					System.out.println("\nThe word to guess is now: " + currentGuess);
					System.out.print("Enter your guess: ");
					if (checkWord(inScanner.next(), solution)) {
						currentGuess = solution;
						break;
					} else {
						System.out.println("That is not the correct word.");
					}

					if (guesssComplete(currentGuess)) {
						flag = false;
					}
					continue;
				}
			}
			System.out.println("Congratulations! " + solution.toUpperCase() + " is the correct word.");
			System.out.println("You achieved the correct answer in " + historyList.size() + " guesses!");
			System.out.print("Would you like a rematch [y/n]? ");
			inScanner = new Scanner(System.in);
			if (inScanner.next().equals("y")) {
				historyList = new ArrayList<>();
				continue;
			} else {
				break;
			}
		}
		System.out.println("\nThanks for playing! Goodbye!");
	}

// Given a Scanner as input, returns a List<String> of strings read from the Scanner.
// The method should read words from the Scanner until there are no more words in the file
// (i.e. inScanner.hasNext() is false). The list of strings should be returned to the calling program.
	private static ArrayList<String> getList(Scanner inScanner) {

		ArrayList<String> list = new ArrayList<String>();
		while (inScanner.hasNext()) {
			list.add(inScanner.nextLine().toLowerCase());
		}
		return list;
	}

// Given two strings as input, compares the first string (guess) to the second
// (solution) character by character. If the two strings are not exactly the same,
// return false. Otherwise return true.
	private static boolean checkWord(String guess, String solution) {
		guess = guess.toLowerCase();
		if (guess.equals(solution)) {
			return true;
		} else {
			return false;
		}

	}

// Given a ArrayList<String> list of strings as input, randomly selects one of the strings
// in the list and returns it to the calling program.
	private static String getRandomWord(ArrayList<String> inList) {
		Random ran = new Random();
		return inList.get(ran.nextInt(inList.size() + 1));
	}

// Given a Scanner as input, prompt the user to enter a character. If the character
// enters anything other than a single character provide an error message and ask
// the user to input a single character. Otherwise return the single character to
// the calling program.
	private static char getCharacterGuess(Scanner inScanner) {
		while (true) {
			System.out.print("Enter a character to guess: ");
			String input = inScanner.next();
			input = input.toLowerCase();
			if (input.length() == 1) {
				return input.charAt(0);
			} else {
				System.out.println("Error. Enter a single character");
			}
		}
	}

// Given a character inChar and a ArrayList<Character> list of characters, check to see if the
// character inChar is in the list. If it is, return true. Otherwise, return false.
	private static boolean checkInList(char inChar, ArrayList<Character> inList) {
		for (int i = 0; i < inList.size(); i++) {
			if (inList.get(i).equals(inChar)) {
				return true;
			}
		}
		return false;
	}

// Given a String, return a String that is the exact same length but consists of
// nothing but '*' characters. For example, given the String DOG as input, return
// the string ***
	private static String starWord(String inWord) {
		StringBuilder outputBuffer = new StringBuilder(inWord.length());
		for (int i = 0; i < inWord.length(); i++) {
			outputBuffer.append("*");
		}
		return outputBuffer.toString();
	}

// Given a character and a String, return the count of the number of times the
// character occurs in that String.
	private static int checkChar(char guessChar, String guessWord) {
		int count = 0;
		for (int i = 0; i < guessWord.length(); i++) {
			if (guessWord.charAt(i) == guessChar) {
				count++;
			}
		}
		return count;
	}

// Given a character, a String containing a word, and a String containing a 'guess'
// for that word, return a new String that is a modified version of the 'guess'
// string where characters equal to the character inChar are uncovered.
// For example, given the following call:
// modfiyGuess('G',"GEOLOGY", "**O*O*Y")
// This functions should return the String "G*O*OGY".
	private static String modifyGuess(char inChar, String word, String currentGuess) {
		StringBuilder newWord = new StringBuilder(currentGuess);
		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) == inChar) {
				newWord.setCharAt(i, inChar);
			}
		}
		return newWord.toString();
	}

	private static boolean guesssComplete(String word) {
		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) == '*') {
				return false;
			}
		}
		return true;
	}
}