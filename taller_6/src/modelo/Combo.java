package modelo;

import java.util.ArrayList;

public class Combo implements Producto {
	private double descuento;
	private String nombreCombo;
	private ArrayList<Producto> elementos_del_combo = new ArrayList<>();
	int precio_suma  = 0;
	int calorias = 0;
	
	public Combo(String nombre, double descuento)
	{
		this.nombreCombo = nombre;
		this.descuento = descuento;
	}
	
	public void agregarItemACombo(Producto itemCombo)
	{
		elementos_del_combo.add(itemCombo);
		calorias += itemCombo.getCalorias();
		precio_suma += itemCombo.getPrecio();
	}
	public String getNombre()
	{
		return nombreCombo;
	}
	public int getPrecio()
	{	
		int precio_descuento = (int) (precio_suma * (1-descuento/100));
		return precio_descuento;
	}
	public int getCalorias()
	{
		return calorias;
	}
	public String generarTextoFactura()
	{
		String linea = "Precio del " + getNombre() + ": " + Integer.toString(getPrecio()) + "\n";
		return linea;
	}
}
