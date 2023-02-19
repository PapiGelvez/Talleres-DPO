package modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Restaurante {	
	private static ArrayList<Ingrediente> lista_ingredientes = new ArrayList<>();
	private static ArrayList<Producto> lista_comidas = new ArrayList<>();
	private static ArrayList<ProductoMenu> lista_productos_base = new ArrayList<>();
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
	public void agregarAPedidoEnCurso()
	{
		boolean espedidomodif = false;
		Pedido ultimo_pedido = lista_pedidos.get(lista_pedidos.size()-1);
		ArrayList<Producto> productos_del_ultimo_pedido = ultimo_pedido.getProductosDelPedido();
		System.out.println("Este es tu pedido hasta ahora");
		if (productos_del_ultimo_pedido.isEmpty())
		{
			System.out.println("0 productos hasta ahora\n");
		}
		for (int j = 0; j < productos_del_ultimo_pedido.size(); j++)
		{
			System.out.println(productos_del_ultimo_pedido.get(j).getNombre());
		}
		
		for (int i = 0; i < lista_comidas.size(); i++)
		{
			System.out.println(Integer.toString(i) + ": " + lista_comidas.get(i).getNombre());
		}
		int pedido = Integer.parseInt(input(("Estas son las opciones del menú, seleccione el número asociado: ")));
		if (lista_comidas.get(pedido) instanceof ProductoMenu)
		{
			int modificar = Integer.parseInt(input("\n¿Desea agregar o quitar ingredientes del producto base?\n1:Sí\n2:No\n"));
			if (modificar == 1)
			{
				espedidomodif = true;
				ProductoAjustado producto_modificado = new ProductoAjustado((ProductoMenu) lista_comidas.get(pedido));
				ArrayList<Ingrediente> lista_ingred = getIngredientes();
				boolean continuar = true;
				while (continuar)
				{
					try
					{
						int opcion = Integer.parseInt(input("1: Agregar\n2: Eliminar\n3:Finalizar\n"));
						if (opcion == 1)
						{
							for (int k = 0; k < lista_ingred.size(); k++)
							{
								System.out.println(Integer.toString(k) + ": " + lista_ingred.get(k).getNombre());
							}
							int agregar = Integer.parseInt(input(("Estos son los ingredientes, seleccione el número asociado del ingrediente que quiere agregar: ")));
							producto_modificado.agregar_ingrediente(lista_ingred.get(agregar).getNombre());
						}
						else if (opcion == 2)
						{
							for (int l = 0; l < lista_ingred.size(); l++)
							{
								System.out.println(Integer.toString(l) + ": " + lista_ingred.get(l).getNombre());
							}
							int eliminar = Integer.parseInt(input(("Estos son los ingredientes, seleccione el número asociado del ingrediente que quiere eliminar: ")));
							producto_modificado.eliminar_ingrediente(lista_ingred.get(eliminar).getNombre());
						}
						else if (opcion == 3)
						{
							productos_del_ultimo_pedido.add(producto_modificado);
							continuar = false;
						}
					}
					catch (NumberFormatException e)
					{
						System.out.println("Debe seleccionar uno de los números de las opciones.");
					}
				}
			}
		}
		if (espedidomodif == false)
		{
			productos_del_ultimo_pedido.add(lista_comidas.get(pedido));
		}
		System.out.println(lista_comidas.get(pedido).getNombre() + " se agregó a tu pedido\n");
	}
	public void getPedidoPorId(int id)
	{
		Pedido pedido_buscado = lista_pedidos.get(id - 1);
		ArrayList<Producto> productos_del_pedido_buscado = pedido_buscado.getProductosDelPedido();
		System.out.println("Estos son los productos del pedido " + Integer.toString(id) + ": ");
		for (int i = 0; i < productos_del_pedido_buscado.size(); i++)
		{
			System.out.println("Nombre del producto: "+ productos_del_pedido_buscado.get(i).getNombre());
			System.out.println("Precio: "+ productos_del_pedido_buscado.get(i).getPrecio());
		}
		System.out.println("\n");
	}
	public static ArrayList<ProductoMenu> getMenuBase()
	{
		return lista_productos_base;
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
			getMenuBase().add(productobase);
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
				for (int j = 0; j < getMenuBase().size(); j++)
				{
					String nombre_base = getMenuBase().get(j).getNombre();
					if (nombre_base.equals(partes[i]))
						{
							combo.agregarItemACombo(getMenuBase().get(j));
						}
				}
			}
			lista_comidas.add(combo);
			lista_combos.add(combo);
			linea = br.readLine();
		}	
		br.close();
	}
	public static String input(String mensaje)
	{
		try
		{
			System.out.print(mensaje);
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}
}
