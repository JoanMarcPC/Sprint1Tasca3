package tasca3n1ex2;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Tasca3n1ex2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<Integer> llista1 = new ArrayList<Integer>();
		List<Integer> llista2 = new ArrayList<Integer>();

		llista1.add(1);
		llista1.add(2);
		llista1.add(3);
		llista1.add(4);
		llista1.add(5);
		llista1.add(6);
		llista1.add(7);
		llista1.add(8);
		llista1.add(9);

		ListIterator<Integer> it1 = llista1.listIterator();
		ListIterator<Integer> it2 = llista2.listIterator();

		while (it1.hasNext()) {

			it2.add(it1.next()); // afegeixo a la llista2 el contingut de llista1 en ordre ascendent
			it2.previous(); // enrere per afegir-los en ordre invers

		}
		for (int num : llista2) {
			System.out.println(num);
		}

	}

}
