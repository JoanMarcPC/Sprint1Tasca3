package tasca3n2ex2;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import tasca3n2ex1.Restaurant;

public class Tasca3n2ex2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashSet<Restaurant> restaurants = new HashSet<Restaurant>();
		List<Restaurant> restaurantsList;
		Restaurant[] restaurantsArray;
		restaurants.add(new Restaurant("Bulli", 50));
		restaurants.add(new Restaurant("Bulli", 100));
		restaurants.add(new Restaurant("Bulli", 1000));
		restaurants.add(new Restaurant("Áncora", 55));
		restaurants.add(new Restaurant("Áncora", 1));
		restaurants.add(new Restaurant("Ancora", 45));
		restaurants.add(new Restaurant("GoXo", 14));
		restaurants.add(new Restaurant("Ñaño", 14));
		restaurants.add(new Restaurant("Zarzamora", 14));
		restaurants.add(new Restaurant("Can Roca", 50));
		restaurants.add(new Restaurant("Can Roca", 80));
		restaurants.add(new Restaurant("Can Rocas", 50));
		restaurants.add(new Restaurant("Can Rocas", 500));
		restaurants.add(new Restaurant("FISH", 12));
		restaurants.add(new Restaurant("F.I.S.H.", 80));
		restaurants.add(new Restaurant("Bulli", 10));
		restaurantsArray = restaurants.toArray(new Restaurant[0]);

		restaurantsList = Arrays.asList(restaurantsArray);
		Collator collatorPrimari = Collator.getInstance(new Locale("es")); // necessari per a tenir en compte ordenació
																			// Español
		collatorPrimari.setStrength(Collator.PRIMARY); //ignorar accents etc
		

		Collections.sort(restaurantsList, new Comparator<Restaurant>() { // clase anonima, si la fés servir mes cops, ho
																			// ficaria en un metode d'una classe i faria lambda
																			// method reference?¿ es correcte el
																			// raonament?
			public int compare(Restaurant r1, Restaurant r2) {
				Collator collatorPrimari = Collator.getInstance(new Locale("es")); // necessari per a tenir en compte
																					// ordenació Español (accents ñ etc)
				collatorPrimari.setStrength(Collator.PRIMARY);
				if (collatorPrimari.compare(r1.getNom(), r2.getNom()) != 0) {
					return collatorPrimari.compare(r1.getNom(), r2.getNom());
				} else {                            // només si el nom es igual, miro la puntuacio
					if (r1.getPuntuacio() > r2.getPuntuacio()) {
						return -1;
					} else {
						return 1;
					}
				}
			}
		});

		// Collections.sort(restaurantsList,Comparator.comparing(Restaurant::getNom).thenComparing(Comparator.comparing(Restaurant::getPuntuacio).reversed()));
		// En principi feia servir aquest. Com fallava amb els accents, he buscat una
		// altra manera

		for (int i = 0; i < restaurantsList.size(); i++) {
			System.out.println(restaurantsList.get(i).toString());
		}

	}

}
