package modelo;

import java.util.ArrayList;

public class ProductoAjustado implements Producto {
	
	private String nombre_producto;
	private ArrayList<Ingrediente> ingredientes_eliminados = new ArrayList<>();
	private ArrayList<Ingrediente> ingredientes_agregados = new ArrayList<>();
	private int precio_base;
	private int calorias_base;
	private int precio_final;
	private int calorias_final;
	
	public ProductoAjustado(ProductoMenu base)
	{
		nombre_producto = base.getNombre();
		precio_base = base.getPrecio();		
		calorias_base = base.getCalorias();
	}
	public String getNombre()
	{
		return this.nombre_producto;
	}
	public void agregar_ingrediente(String ingrediente)
	{
		ArrayList<Ingrediente> lista_ingredientes = Restaurante.getIngredientes();
		for (int i=0; i < lista_ingredientes.size(); i++)
		{
			String nombre_ingrediente = lista_ingredientes.get(i).getNombre();
			if (nombre_ingrediente.equals(ingrediente))
			{
				ingredientes_agregados.add(lista_ingredientes.get(i));
				break;
			}
		}
	}
	public void eliminar_ingrediente(String ingrediente)
	{
		ArrayList<Ingrediente> lista_ingredientes = Restaurante.getIngredientes();
		for (int i=0; i < lista_ingredientes.size(); i++)
		{
			String nombre_ingrediente = lista_ingredientes.get(i).getNombre();
			if (nombre_ingrediente.equals(ingrediente))
			{
				ingredientes_eliminados.add(lista_ingredientes.get(i));
			}
		}
	}
	public int getPrecio()
	{
		int precio_suma = 0;
		int precio_resta = 0;
		for (int i = 0; i < ingredientes_agregados.size(); i++)
		{
			if (!ingredientes_agregados.isEmpty())
			{
				int precio_ingrediente = ingredientes_agregados.get(i).getCostoAdicional();
				precio_suma += precio_ingrediente;
			}
		}
		for (int i = 0; i < ingredientes_eliminados.size(); i++)
		{
			if (!ingredientes_eliminados.isEmpty())
			{
				int precio_ingrediente = ingredientes_eliminados.get(i).getCostoAdicional();
				precio_resta += precio_ingrediente;
			}
		}
		precio_final = precio_base + precio_suma - precio_resta;
		return precio_final;
	}
	public int getCalorias()
	{
		int calorias_suma = 0;
		int calorias_resta = 0;
		for (int i = 0; i < ingredientes_agregados.size(); i++)
		{
			if (!ingredientes_agregados.isEmpty())
			{
				int calorias = ingredientes_agregados.get(i).getCalorias();
				calorias_suma += calorias;
			}
		}
		for (int i = 0; i < ingredientes_eliminados.size(); i++)
		{
			if (!ingredientes_eliminados.isEmpty())
			{
				int calorias = ingredientes_eliminados.get(i).getCalorias();
				calorias_resta += calorias;
			}
		}
		calorias_final = calorias_base + calorias_suma - calorias_resta;
		return calorias_final;
	}
	public String generarTextoFactura()
	{
		String linea = "Precio del producto modificado " + getNombre() + ": ";
		linea += Integer.toString(getPrecio()) + "\n";
		if (!ingredientes_eliminados.isEmpty())
		{
			for (int j = 0; j < ingredientes_eliminados.size(); j++)
			{
				linea += "Sin " + ingredientes_eliminados.get(j).getNombre() + " -" +Integer.toString(ingredientes_eliminados.get(j).getCostoAdicional()) + "\n";
			}
		}
		if (!ingredientes_agregados.isEmpty())
		{
			for (int j = 0; j < ingredientes_agregados.size(); j++)
			{
				linea += "Con " + ingredientes_agregados.get(j).getNombre() + " +" + Integer.toString(ingredientes_agregados.get(j).getCostoAdicional()) + "\n";
			}
		}
		return linea;
	}
}
