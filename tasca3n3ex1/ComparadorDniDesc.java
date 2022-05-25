package tasca3n3ex1;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

public class ComparadorDniDesc implements Comparator<Persona> {
	@Override
	public int compare(Persona p1, Persona p2) {
		// TODO Auto-generated method stub
		Collator collatorPrimari = Collator.getInstance(new Locale("es")); // necessari per a tenir en compte ordenació
																			// Español (accents ñ etc)
		collatorPrimari.setStrength(Collator.PRIMARY);
		return collatorPrimari.compare(p2.getDni(), p1.getDni());
	}
}
