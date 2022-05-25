package tasca3n1ex1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class Tasca3n1ex1 {

	public static void main(String[] args) {

		ArrayList<Month> months = new ArrayList<Month>();
		months.add(new Month("Gener"));
		months.add(new Month("Febrer"));
		months.add(new Month("Març"));
		months.add(new Month("Abril"));
		months.add(new Month("Maig"));
		months.add(new Month("Juny"));
		months.add(new Month("Juliol"));
		months.add(new Month("Setembre"));
		months.add(new Month("Octubre"));
		months.add(new Month("Novembre"));
		months.add(new Month("Desembre\n"));

		months.add(7, new Month("Agost"));

		for (Month mes : months) {
			System.out.println(mes.getName());
		}

		HashSet<Month> months2 = new HashSet<Month>();

		months2.addAll(months);

		for (int i = 0; i < months.size(); i++)
			System.out.println(months.get(i).getName());

		Iterator<Month> it = months.iterator();
		while (it.hasNext()) {
			System.out.println(it.next().getName());
		}

	}

}
