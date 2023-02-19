package modelo;

import java.util.ArrayList;

public class Combo implements Producto {
	private double descuento;
	private String nombreCombo;
	private ArrayList<ProductoMenu> elementos_del_combo = new ArrayList<>();
	int precio_final;
	
	public Combo(String nombre, double descuento)
	{
		this.nombreCombo = nombre;
		this.descuento = descuento;
	}
	
	public void agregarItemACombo(ProductoMenu itemCombo)
	{
		elementos_del_combo.add(itemCombo);
	}
	public String getNombre()
	{
		return nombreCombo;
	}
	public int getPrecio()
	{	
		precio_final = 0;
		for (int i=0; i < elementos_del_combo.size(); i++)
		{
			ProductoMenu producto = elementos_del_combo.get(i);
			int precio = producto.getPrecio();
			precio_final += precio;
		}
		precio_final = (int) (precio_final * (1-descuento/100));
		return precio_final;
	}
	public String generarTextoFactura()
	{
		String linea = "Precio del " + getNombre() + ": " + Integer.toString(getPrecio()) + "\n";
		return linea;
	}
}
