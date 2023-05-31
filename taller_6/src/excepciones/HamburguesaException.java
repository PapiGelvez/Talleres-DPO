package excepciones;

public abstract class HamburguesaException extends Exception {
	private String nombreComidaRepetida;
	
	public String getMessage() {
		return "La comida " + nombreComidaRepetida + " está repetida";
	}
}
