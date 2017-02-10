import java.util.Scanner;
import java.util.ArrayList;
import java.util.*;

class Guess {
	public static int number_of_guesses = 0;

	public int number1;
	public int number2;
	public int number3;
	public boolean not_complete = false;
	public boolean fits_guess;

	public void check_guess() {
		if (number1 < number2 && number2 < number3) {
			this.fits_guess = true;
		}
		else {
			this.fits_guess = false;
		}
		number_of_guesses++;
	}

	public void get_guess() {
		System.out.println(number1 + " , " + number2 + " , " + number3);
	}

	public void set_number(int number_on, int number_guess) {
		if (number_on == 0) {
			this.number1 = number_guess;
		}

		else if (number_on == 1) {
			this.number2 = number_guess;
		}

		else if (number_on == 2) {
			this.number3 = number_guess;
		}
	}
}



public class Guessing_Game {

	public static void main(String[] args) {
		List<Guess> guesses = new ArrayList<Guess>();
		Scanner kb = new Scanner(System.in);
		String line = "";
		int numbers[] = new int[3];
		boolean hack;
		
		//gives introduction to game
		System.out.println("Let's play a game. I am nature, and I have a rule. You can perform experiments to test my rule, and I will tell you whether they conform to the rule or not. The way you perform tests is by entering three numbers. When you feel you have performed suffecient tests in order to confirm your theory, type 'finished'. If you want to see your previous guesses and whether or not they fit the rule, type 'previous'.");
		System.out.println("Your hint is that the sequence 2, 4, 6 conforms to the rule.");
		
		//runs tests until user enters 'finished'
		while (line.equals("finished") != true) {
			Guess guess = new Guess();
			System.out.println("Please enter your first number:");
			
			//loop that takes the users numbers
			for (int i = 0; i < 3; i++) {
				hack = false;
				line = kb.nextLine();
				
				//if user enters 'finished', stop taking numbers
				if (line.equals("finished")) {
					guess.not_complete = true;
					break;
				}

				if (line.equals("previous")) {
					System.out.println("There have been " + Guess.number_of_guesses + " guesses: ");
					int count = 1;
					for (Guess each: guesses) {
						String passes = (each.fits_guess) ? "does" : "doesn't";
						System.out.println("Guess " + count + ": " + each.number1 + " " + each.number2 + " " + each.number3 + ". It " + passes + " fit the rule.");
						count++;
					}
					guess.not_complete = true;
					break;
				}

				if (line.equals("guess")) {
					System.out.println("Print your guess: ");
					line = kb.nextLine();
					if (line.equals("increasing")) {
						line = "finished";
						guess.not_complete = true;
						hack = true;
						break;
					}
					else {
						guess.not_complete = true;
						hack = true;
						break;
					}
				}

				//try to insert input into array, print error if user input is an invalid type
				try {
					guess.set_number(i, Integer.parseInt(line));
				}
				catch (Exception e) {
					if (!hack) {
						System.err.println("Invalid input, please try again.");
						guess.not_complete = true;
						break;
					}
				}
			}
			
			//checks results of test (unless user inputed 'finished')
			if (guess.not_complete == false) {
				guess.check_guess();
				guesses.add(guess);
				System.out.println("The result of your test was: " + String.valueOf(guess.fits_guess));
			}
		}
		
		System.out.println("The rule was: any three integers in consecutive order.");
		System.out.println("Great job!");
	}
}
