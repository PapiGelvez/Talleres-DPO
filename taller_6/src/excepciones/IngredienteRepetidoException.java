package excepciones;

public class IngredienteRepetidoException extends HamburguesaException {
	private String nombreIngredienteRepetido;
	
	public IngredienteRepetidoException(String nombre) {
		this.nombreIngredienteRepetido = nombre;
	}
	
	@Override
	public String getMessage() {
		return "El ingrediente " + nombreIngredienteRepetido + " está repetido";
	}
}
