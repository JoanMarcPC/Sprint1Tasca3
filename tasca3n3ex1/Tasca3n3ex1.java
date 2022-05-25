package tasca3n3ex1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Tasca3n3ex1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Persona> personas = new ArrayList<Persona>();
		HashSet<String> dnis = new HashSet<String>();
		final String PATH = "C:\\Users\\jmpca\\git\\Itacademy\\Sprint1\\src\\tasca3n3ex1\\Personas.csv";
		int option;
		int sizeHashSet;
		Persona p1;

		personas = llegirCsv(PATH); // Suposant que les dades del CSV son correctes (sense dnis repetits per
									// exemple)

		for (int i = 0; i < personas.size(); i++) { // afegir dnis a HashSet per controlar que no es puguin afegir
													// posteriorment persona amb = dni
			dnis.add(personas.get(i).getDni());
		}

		do {
			do {
				option = introduirEnter();
			} while (option == -1);
			switch (option) {
			case 0:
				break;
			case 1:
				p1 = introduirPersona();
				sizeHashSet = dnis.size();
				dnis.add(p1.getDni());
				if (sizeHashSet == dnis.size()) {
					System.out.println(
							"No s'ha pogut afegir a la persona\n No es pot afegir una persona amb un DNI igual a una ja existent");
				} else {
					personas.add(p1);
				}
				break;
			case 2:
				Collections.sort(personas, new ComparadorNombre());
				mostrarPersones(personas);
				break;
			case 3:
				Collections.sort(personas, new ComparadorNombreDesc());
				mostrarPersones(personas);
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				break;
			case 7:
				break;
			case 8:
				break;
			case 9:
				break;
			default:
				System.out.println("Introdueix un enter que correspongui a una de les opcions disponibles.");
				break;

			}

		} while (option != 0);

	}

	public static ArrayList<Persona> llegirCsv(String readPath) {
		final String SEPARADOR_CAMPS = ",";
		String inputString;
		String[] array;
		boolean EndFile = false;
		ArrayList<Persona> personas = new ArrayList<Persona>();

		try {
			BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(readPath), "ISO-8859-1"));
			try {

				while (!EndFile) {

					inputString = bf.readLine();

					if (inputString == null) {
						EndFile = true;
					} else {
						array = inputString.split(SEPARADOR_CAMPS, 3);
						personas.add(new Persona(array[0], array[1], array[2]));

					}
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (UnsupportedEncodingException e) { // 1r try
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) { // 1r try
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return personas;

	}

	public static Persona introduirPersona() {

		String nom = "";
		String cognom = "";
		String dni = "";

		Scanner input = new Scanner(System.in);
		System.out.println("Introdueix el nom de la nova persona");
		nom = input.nextLine();
		System.out.println("Introdueix els cognoms de la nova persona");
		cognom = input.nextLine();
		dni = validarDni();
		return new Persona(nom, cognom, dni);

	}

	public static int introduirEnter() {
		int option;
		Scanner input = new Scanner(System.in);
		System.out.println(
				"Introdueix l'enter de l'opcio desitjada.\n1.- Introduir persona.\n2.- Mostrar les persones ordenades per nom (A-Z).\n3.- Mostrar les persones ordenades per nom (Z-A).\n4.- Mostrar les persones ordenades per cognoms (A-Z).\n5.- Mostrar les persones ordenades per cognoms (Z-A).\n6.- Mostrar les persones ordenades per DNI (1-9).\n7.- Mostrar les persones ordenades per DNI (9-1).\n0.- Sortir.");
		try {
			option = input.nextInt();
			return option;
		} catch (InputMismatchException e) {
			System.out.println("Introdueix una dada del tipus enter si us plau");
			return -1;
		}
	}

	public static String validarDni() {

		final char[] LLETRES_COINCIDENTS = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z',
				'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E' }; // les posicions de l'array coincideixen amb el residu de
															// dividir els numeros entre 23
		String dni = "";
		int nums; // a la primera fase la faig servir per controlar que hi ha 8 digits, després
					// contindra lString de digits
		char lletra;
		char[] caractersDni;
		boolean okStringDni = false;
		boolean okNums; // només el paso a false quan detecto que no hi ha 8 nombres a l'String
						// d'entrada
		Scanner input = new Scanner(System.in);
		do {

			do {
				okNums = true;
				nums = 0;
				System.out.println("Introdueix un DNI: 8 nombres i 1 lletra si us plau");
				dni = input.nextLine();

				if (dni.length() == 9) {
					caractersDni = dni.toCharArray();
					if (!Character.isLetter(caractersDni[caractersDni.length - 1])) {
						System.out.println("Error de format\n L'últim caracter ha de ser una lletra");
					} else {
						okNums = true;
						for (int i = 0; i < caractersDni.length - 1 && okNums; i++) {
							if (Character.isDigit(caractersDni[i])) {
								nums += 1;
							} else {
								okNums = false;
								System.out.println("Error de format\n Els primers 8 caracters han de ser nombres");
							}
						}
					}

				} else {
					System.out.println("Error de longitud del DNI\n Han de ser 9 caracters");
				}
			} while (nums != 8);

			lletra = dni.charAt(dni.length() - 1);
			nums = Integer.parseInt(dni.substring(0, 8));
			nums %= 23;

			lletra = Character.toUpperCase(lletra); // char to upperCase per si de cas
			if (lletra == LLETRES_COINCIDENTS[nums]) {
				System.out.println("El DNI introduït es vàlid");
				okStringDni = true; //
			} else {
				System.out.println(
						"El DNI introduït no es correcte\n La lletra indicada no fa match amb els digits introduits");
			}

		} while (!okStringDni);
		return dni;
	}

	public static void mostrarPersones(ArrayList<Persona> persones) { // no queda bé pero no em vull matar a fer que
																		// quedi bonic
		System.out.println("___Nom_________ ____Cognoms______________ ____NIF_______ ");
		
		for (int i = 0; i < persones.size(); i++) {

			System.out.print(persones.get(i).getNom());
			
			System.out.print("            "+ persones.get(i).getCognom());
			
			
			
			System.out.println("         "+ persones.get(i).getDni());
		}
	}
}
