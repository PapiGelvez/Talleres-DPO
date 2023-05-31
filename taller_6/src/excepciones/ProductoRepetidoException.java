package excepciones;

public class ProductoRepetidoException extends HamburguesaException {
	private String nombreProductoRepetido;
	
	public ProductoRepetidoException(String nombre) {
		this.nombreProductoRepetido = nombre;
	}
	
	@Override
	public String getMessage() {
		return "El ingrediente " + nombreProductoRepetido + " está repetido";
	}
}
