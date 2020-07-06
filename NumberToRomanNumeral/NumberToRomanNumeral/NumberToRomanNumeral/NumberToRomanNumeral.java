package NumberToRomanNumeral;
/*
 * Number to Roman Numeral
 * 
 *   A program that converts decimal numbers to Roman numerals up to 3999.
 * 
 * @author Jared Heidotting
 * 
 */

import java.util.Scanner;


public class NumberToRomanNumeral {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		int number = promptUserForNumber(keyboard);
		String answer = convertNumberToNumeral(number);
		System.out.println("The number " + number + " is the Roman Numeral " + answer);
	}

	// Given a Scanner as input, prompts the user to input a number between 1 and 3999.
	// Checks to make sure the number is within range, and provides an error message until
	// the user provides a value within range.  Returns the number input by the user to the
	// calling program.
	private static int promptUserForNumber(Scanner inScanner) {
		System.out.print("Please enter a number between 1 and 3999:");
		int number = inScanner.nextInt();
		if ((number < 1) || (number > 3999)) {
			System.out.println("Error! Invalid number choice.");
			System.out.print("Please enter a number between 1 and 3999:");
			number = inScanner.nextInt();
		}
		return number;
	}
	
	// Given a number as input, converts the number to a String in Roman numeral format, 
	// following the rules in the write-up for Lab 09.  Returns the String to the calling
	// program.  NOTE:  This method can possibly get long and complex.  Use the 
	// convertDigitToNumeral method below to break this up and make it a bit simpler to code.
	private static String convertNumberToNumeral(int number) {
		String romanNumeral = "";
		romanNumeral += convertDigitToNumeral(number/1000%10, 'M', 'x', 'x');
		romanNumeral += convertDigitToNumeral(number/100%10, 'C', 'D', 'M');
		romanNumeral += convertDigitToNumeral(number/10%10, 'X', 'L', 'C');
		romanNumeral += convertDigitToNumeral(number%10, 'I', 'V', 'X' );
		return romanNumeral;
	}
	
	// Given a digit and the Roman numerals to use for the "one", "five" and "ten" positions,
	// returns the appropriate Roman numeral for that digit.  For example, if the number to
	// convert is 49 we would call convertDigitToNumeral twice.  The first call would be:
	//     convertDigitToNumeral(9, 'I','V','X')
	// and would return a value of "IX".  The second call would be:
	//     convertDigitToNumeral(4, 'X','L','C')
	// and would return a value of "XL".  Putting those together we would see that 49 would be the
	// Roman numeral XLIX.
	// Call this method from convertNumberToNumeral above to convert an entire number into a 
	// Roman numeral.
	private static String convertDigitToNumeral(int digit, char one, char five, char ten) {
		String numeral = "";
		if (digit >= 0 && digit <= 9) {
			if (digit == 9) {
				numeral += ten;
				digit = digit - 10;
			}
			else if(digit >= 4) {
				numeral += five;
				digit = digit - 5;
			}
			while(digit > 0) {
				numeral += one;
				digit = digit - 1;
			}
			while(digit < 0) {
				numeral += one;
				digit = digit + 1;
			}
		}
		else {
			return numeral;
		}
		return numeral;
	}
	
}
