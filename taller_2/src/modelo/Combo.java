package modelo;

import java.util.ArrayList;

public class Combo implements Producto {
	private double descuento;
	private String nombreCombo;
	private ArrayList<Producto> elementos_del_combo;
	
	public Combo(String nombre, double descuento)
	{
		this.nombreCombo = nombre;
		this.descuento = descuento;
	}
	
	public void agregarItemACombo(Producto itemCombo)
	{
		this.elementos_del_combo.add(itemCombo);
	}
	public String getNombre()
	{
		return nombreCombo;
	}
	public int getPrecio()
	{	
		int precio_final = 0;
		for (int i=0; i < elementos_del_combo.size(); i++)
		{
			Producto producto = elementos_del_combo.get(i);
			int precio = producto.getPrecio();
			precio_final += precio;
		}
		precio_final = (int) (precio_final * (1-descuento/100));
		return precio_final;
	}
	public String generarTextoFactura()
	{
		String linea = "Precio del combo" + getNombre() + ": " + Integer.toString(getPrecio());
		return linea;
	}
}
