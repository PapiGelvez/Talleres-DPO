package modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Restaurante {	
	private static ArrayList<Ingrediente> lista_ingredientes = new ArrayList<>();
	private static ArrayList<Producto> lista_comidas = new ArrayList<>();
	private static ArrayList<Combo> lista_combos = new ArrayList<>();
	private static ArrayList<Pedido> lista_pedidos = new ArrayList<>();
	
	public Restaurante() throws FileNotFoundException, IOException
	{
		String archivoIngredientes = "data/ingredientes.txt";
		String archivoMenu = "data/menu.txt";
		String archivoCombos = "data/combos.txt";
		
		cargarInformacionRestaurante(archivoIngredientes, archivoMenu, archivoCombos);
		
	}
	public void iniciarPedido(String nombreCliente, String direccionCliente)
	{
		Pedido pedido_iniciado = new Pedido(nombreCliente, direccionCliente);
		lista_pedidos.add(pedido_iniciado);
	}
	public void cerrarYGuardarPedido()
	{
		Pedido ultimo_pedido = lista_pedidos.get(lista_pedidos.size()-1);
		String nombre_archivo = Integer.toString(ultimo_pedido.getIdPedido());
		File archivo = new File(nombre_archivo + ".txt");
		ultimo_pedido.guardarFactura(archivo);
	}
	public Pedido getPedidoEnCurso()
	{
		Pedido ultimo_pedido = lista_pedidos.get(lista_pedidos.size()-1);
		ArrayList<Producto> lista_productos = ultimo_pedido.getProductosDelPedido();
		System.out.println("Este es tu pedido hasta ahora");
		
		for (int i = 0; i < lista_productos.size(); i++)
		{
			System.out.println(lista_productos.get(i).getNombre());
		}
		return ultimo_pedido;
	}
	public static ArrayList<Producto> getMenuBase()
	{
		return lista_comidas;
	}
	public static ArrayList<Ingrediente> getIngredientes()
	{
		return lista_ingredientes;
	}
	public static void cargarInformacionRestaurante(String archivoIngredientes, String archivoMenu, String archivoCombos) throws FileNotFoundException, IOException
	{
		cargarIngredientes(archivoIngredientes);
		cargarMenu(archivoMenu);
		cargarCombos(archivoCombos);
	}
	private static void cargarIngredientes(String archivoIngredientes) throws FileNotFoundException, IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(archivoIngredientes));
		String linea = br.readLine();
		
		while (linea != null)
		{
			String[] partes = linea.split(";");
			String nombreIngrediente = partes[0];
			int precioIngrediente = Integer.parseInt(partes[1]);
			
			Ingrediente ingrediente = new Ingrediente(nombreIngrediente, precioIngrediente);
			lista_ingredientes.add(ingrediente);
			linea = br.readLine();
		}	
		br.close();
	}
	private static void cargarMenu(String archivoMenu) throws FileNotFoundException, IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(archivoMenu));
		String linea = br.readLine();
		
		while (linea != null)
		{
			String[] partes = linea.split(";");
			String nombre = partes[0];
			int precioBase = Integer.parseInt(partes[1]);
			
			ProductoMenu productobase = new ProductoMenu(nombre, precioBase);
			lista_comidas.add(productobase);
			linea = br.readLine();
		}	
		br.close();
	}
	private static void cargarCombos(String archivoCombos) throws FileNotFoundException, IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(archivoCombos));
		String linea = br.readLine();
		
		while (linea != null)
		{
			String[] partes = linea.split(";");
			String nombreCombo = partes[0];
			double descuento = Double.parseDouble(partes[1].replace("%", ""));
			Combo combo = new Combo(nombreCombo, descuento);
			for (int i = 2; i < partes.length; i++)
			{
				for (int j = 0; j < lista_comidas.size(); j++)
				{
					if (lista_comidas.get(j).getNombre() == partes[i])
						{
							combo.agregarItemACombo(lista_comidas.get(j));
						}
				}
			}
			lista_comidas.add(combo);
			lista_combos.add(combo);
			linea = br.readLine();
		}	
		br.close();
	}
}
