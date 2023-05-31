package excepciones;

public class PrecioMaximoException extends Exception {
	private int sumaPedido;
	
	public void setSumaPedido(int sumapedido) {
		this.sumaPedido = sumapedido;
	}
	
	public String getMessage() {
		return "Al agregar ese último producto, su compra excede los 150000 pesos";
	}
}
