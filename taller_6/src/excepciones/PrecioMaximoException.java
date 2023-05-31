package excepciones;

public class PrecioMaximoException extends Exception {
	private int sumaPedido;
	
	public PrecioMaximoException(int sumaPedido) {
		this.sumaPedido = sumaPedido;
	}
	public void setSumaPedido(int sumapedido) {
		this.sumaPedido = sumapedido;
	}
	
	public String getMessage() {
		return "Error: Tu pedido esta en " + sumaPedido + " pesos y agregar este producto superaría los 150000 pesos";
	}
}
