package modelo;

public class ProductoMenu implements Producto {
	private String nombre;
	private int precioBase;
	
	public ProductoMenu(String nombre, int precioBase)
	{
		this.nombre = nombre;
		this.precioBase = precioBase;
	}
	
	public String getNombre()
	{
		return this.nombre;
	}
	public int getPrecio()
	{
		return this.precioBase;
	}
	public String generarTextoFactura()
	{
		String linea = "Precio del producto " + getNombre() + ": " + Integer.toString(getPrecio()) + "\n";
		return linea;
	}
}