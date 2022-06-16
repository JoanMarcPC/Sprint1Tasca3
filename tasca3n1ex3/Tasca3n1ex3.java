package tasca3n1ex3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import tasca3n1ex1.Month;

import java.util.Collections;
import java.io.*;

public class Tasca3n1ex3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final String READ_PATH = "C:\\Users\\formacio\\git\\Itacademy\\Sprint1\\src\\tasca3n1ex3\\countries.txt";
		final String WRITE_PATH = "C:\\Users\\formacio\\git\\Itacademy\\Sprint1\\src\\tasca3n1ex3\\classificacio.txt";
		int option = 10;
		String name;
		int score;
		Scanner UserInput = new Scanner(System.in);
		boolean ok = false;
		HashMap<String, String> game = new HashMap<String, String>();

		try {
			game = CreateHashMap(READ_PATH);
		} catch (UnsupportedEncodingException e) {
			System.out.println("Issue encoding UTF8(read)");
		}
		if (game != null) {
			do {
				while (!ok) {
					try {
						System.out.println("Welcome! Choose an option:\n 0.- Quit\n 1.- Play\n 2.- Clear Scores");
						option = UserInput.nextInt();
						ok = true;
					} catch (InputMismatchException e) {
						System.out.println("Enter an integer please");
						UserInput.nextLine();
						ok = false;
					}
					UserInput.nextLine();
				}
				ok = false;
				switch (option) {
				case 0:
					System.out.println("GoodBye!");
					break;
				case 1:

					name = readName();
					score = play(game);
					write(name, score, WRITE_PATH);
					break;
				case 2:
					cleanTxt(WRITE_PATH);
					System.out.println("Scores cleared");
				}
			} while (option != 0);
			UserInput.close();
		}
	}

	public static String readName() {
		String name = "";
		Scanner input = new Scanner(System.in);
		System.out.println("Enter your name");
		name += input.nextLine();

		return name;
	}

	public static String[] readString(String inputString) {
		String[] countryCapital = inputString.split(" ", 2); // a la posició 0 queda country y a la posició 1 capital

		countryCapital[0] = countryCapital[0].replace("_", " ");
		countryCapital[1] = countryCapital[1].replace("_", " ");

		return countryCapital;
	}

	public static HashMap<String, String> CreateHashMap(String readPath) throws UnsupportedEncodingException {
		try {
			BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(readPath), "utf-8"));
			// BufferedReader bf = new BufferedReader( new
			// FileReader("C:\\Users\\jmpca\\git\\Itacademy\\Sprint1\\src\\tasca3n1ex3\\countries.txt"));
			// sense codificació

			String inputString = "";
			String[] countryCapital;
			HashMap<String, String> game = new HashMap<String, String>();
			try {
				boolean EndFile = false;
				while (!EndFile) {

					inputString = bf.readLine();

					if (inputString.isEmpty()) {
						EndFile = true;
					} else {
						countryCapital = readString(inputString);
						game.put(countryCapital[0], countryCapital[1]);
					}
				}
				bf.close();

				return game;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Buffer problem (Read)");
				return null;
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File not found (Read)");
			return null;
		}
	}

	public static int play(HashMap<String, String> game) {
		final int NUMTIMES = 10;
		int score = 0;
		int random;
		Scanner input = new Scanner(System.in);

		Set<String> listCountries = game.keySet();// coleccio Set treta de HashMap que conte countries

		String[] arrayCountries = listCountries.toArray(new String[0]); // Paso a array per poder accedir a la
		// coleccio (ara array) aleatoriamente
		Set<Integer> setRandom = randomWithNoDuplicates(NUMTIMES, arrayCountries.length); // collecio que conte num
																							// aleatori no repetit

		Iterator<Integer> it = setRandom.iterator();

		for (int i = 0; i < NUMTIMES; i++) {

			String country;
			String answer = "";
			random = it.next();
			country = arrayCountries[random];
			System.out.println("Which is the capital of " + country + "?");
			answer = "";
			answer = input.nextLine();
			System.out.println(answer);

			if (answer.equalsIgnoreCase(game.get(country))) {
				System.out.println("You win!\nPress enter to continue");
				score += 1;
			} else {
				System.out.println("You lose...");
				System.out.println(
						"The capital of " + country + " is " + game.get(country) + "\nPress enter to continue");
			}

			input.nextLine(); // netejar el scanner

		}
		System.out.println("Your score is " + score + " points");
		return score;

	}

	public static void write(String name, int score, String path) {
		String nameScore = name + ": " + score + " points";
		BufferedWriter bf;
		try {
			bf = new BufferedWriter(new FileWriter(path, true)); // No acabo d'entendre pq aqui no cal codificar a UTf 8
																	// pero al llegir si
			bf.write("" + nameScore + "\n");
			bf.close();
		} catch (IOException e) {
			System.out.println("Buffer problem (Write)");
		}

	}

	public static void cleanTxt(String path) {
		BufferedWriter bf;
		try {
			bf = new BufferedWriter(new FileWriter(path, false)); // false perque NO append
			bf.write("");
			bf.close();
		} catch (IOException e) {
			System.out.println("Buffer problem (clean)");
		}

	}

	public static Set<Integer> randomWithNoDuplicates(int numTimes, int lengthCountries) {
		Random randNum = new Random();
		Set<Integer> setRandom = new LinkedHashSet<Integer>();
		while (setRandom.size() < numTimes) { // numTimes = quantitat de numeros random
			setRandom.add(randNum.nextInt(lengthCountries - 1) + 1); // lengthCountries (-1 sino surt de l'array) fins
																		// quin num pot arribar a ser el random
																		// (0->lengthCountries)
		}
		return setRandom;
	}

}
