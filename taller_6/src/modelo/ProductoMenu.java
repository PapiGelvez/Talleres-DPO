package modelo;

public class ProductoMenu implements Producto {
	private String nombre;
	private int precioBase;
	private int calorias;
	
	public ProductoMenu(String nombre, int precioBase, int calorias)
	{
		this.nombre = nombre;
		this.precioBase = precioBase;
		this.calorias = calorias;
	}
	
	public String getNombre()
	{
		return this.nombre;
	}
	public int getPrecio()
	{
		return this.precioBase;
	}
	public int getCalorias()
	{
		return this.calorias;
	}
	public String generarTextoFactura()
	{
		String linea = "Precio del producto " + getNombre() + ": " + Integer.toString(getPrecio()) + "\n";
		return linea;
	}
}