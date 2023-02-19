package consola;

import modelo.Restaurante;
import modelo.Pedido;
import modelo.Producto;
import modelo.Ingrediente;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Aplicacion {
	
	private Restaurante nuevo_restaurante = null;
	public static void mostrarMenu()
	{
		System.out.println("Opción 1: cargar datos");
		System.out.println("Opción 2: Iniciar un nuevo pedido");
		System.out.println("Opción 3: Agregar un elemento a un pedido");
		System.out.println("Opción 4: Cerrar el pedido y guardar la factura");
		System.out.println("Opción 5: Consultar la información de un pedido dado su id");
		System.out.println("Opción 6: Salir de la aplicación\n");
	}
	public void ejecutarOpcion() throws FileNotFoundException, IOException
	{
		boolean continuar = true;
		while (continuar)
		{
			try
			{
				mostrarMenu();
				int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
				if (opcion_seleccionada == 1)
				{
					Restaurante nuevo_restaurante = new Restaurante();
				}
				else if (opcion_seleccionada == 2 && nuevo_restaurante != null)
				{
					String nombreCliente = input("Ingrese su nombre:");
					String direccionCliente = input("Ingrese su direccion:");
					nuevo_restaurante.iniciarPedido(nombreCliente, direccionCliente);
				}
				else if (opcion_seleccionada == 3)
				{
					ArrayList<Producto> lista_comida = nuevo_restaurante.getMenuBase();
					ArrayList<Ingrediente> lista_ingredientes = nuevo_restaurante.getIngredientes();
					for (int i = 0; i < lista_comida.size(); i++)
					{
						String nombre = lista_comida.get(i).getNombre();
						System.out.println(Integer.toString(i) + nombre);
					}
					int numero_producto = Integer.parseInt(input("Ingrese el producto que quiere agregar al pedido:"));
					Pedido
				}
				else if (opcion_seleccionada == 4)
				{
					
				}
				else if (opcion_seleccionada == 5)
				{
					
				}
				else if (opcion_seleccionada == 6)
				{
					System.out.println("Saliendo de la aplicación ...");
					continuar = false;
				}
				else if (nuevo_restaurante == null)
				{
					System.out.println("Para poder ejecutar esta opción primero debe cargar un archivo de atletas.");
				}
				else
				{
					System.out.println("Por favor seleccione una opción válida.");
				}
			}
			catch (NumberFormatException e)
			{
				System.out.println("Debe seleccionar uno de los números de las opciones.");
			}
		}
		
	}
	public static String input(String mensaje)
	{
		try
		{
			System.out.print(mensaje + ": ");
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
	public static void main(String[] args) throws NumberFormatException, FileNotFoundException, IOException
	{
		Aplicacion consola = new Aplicacion();
		consola.ejecutarOpcion();
	}
}
