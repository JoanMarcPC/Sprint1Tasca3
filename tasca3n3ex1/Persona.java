package tasca3n3ex1;

public class Persona {
	private String nom;
	private String cognom;
	private String Dni;

	public Persona(String nom, String cognom, String dni) {

		this.nom = nom;
		this.cognom = cognom;
		Dni = dni;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCognom() {
		return cognom;
	}

	public void setCognom(String cognom) {
		this.cognom = cognom;
	}

	public String getDni() {
		return Dni;
	}

	public void setDni(String dni) {
		Dni = dni;
	}

}
