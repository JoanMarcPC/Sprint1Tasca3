package tasca3n2ex1;

import java.util.HashSet;
import java.util.Iterator;

public class Tasca3n2ex1 {
	public static void main(String[] args) {
		HashSet<Restaurant> restaurants = new HashSet<Restaurant>();
		restaurants.add(new Restaurant("Bulli", 50));
		restaurants.add(new Restaurant("Bulli", 100));
		restaurants.add(new Restaurant("Bulli", 50)); // aquest mai s'arriba a afegir (cal fer previament hashCode i
														// equals per que la coleccio hashSet pugui interpretar
														// correctament un objecte de la classe restaurant igual

		System.out.println(restaurants.size()); // es pot veure que només té 2 elements
		Iterator<Restaurant> it = restaurants.iterator();
		while (it.hasNext()) {
			Restaurant restaurant = it.next();
			System.out.println(restaurant.getNom());
			System.out.println(restaurant.getPuntuacio());
		}
	}
}
