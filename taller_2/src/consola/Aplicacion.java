package consola;

import modelo.Restaurante;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Aplicacion {
	
	private Restaurante nuevo_restaurante;
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
				int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción: \n"));
				if (opcion_seleccionada == 1)
				{
					nuevo_restaurante = new Restaurante();
				}
				else if (opcion_seleccionada == 2)
				{
					String nombreCliente = input("Ingrese su nombre:\n");
					String direccionCliente = input("Ingrese su direccion:\n");
					nuevo_restaurante.iniciarPedido(nombreCliente, direccionCliente);
				}
				else if (opcion_seleccionada == 3)
				{
					nuevo_restaurante.agregarAPedidoEnCurso();
				}
				else if (opcion_seleccionada == 4)
				{
					nuevo_restaurante.cerrarYGuardarPedido();
				}
				else if (opcion_seleccionada == 5)
				{
					int id = Integer.parseInt(input("Por favor ingrese el id de la factura que quiere buscar: \n"));
					nuevo_restaurante.getPedidoPorId(id);
				}
				else if (opcion_seleccionada == 6)
				{
					System.out.println("Saliendo de la aplicación ...");
					continuar = false;
				}
				else if (nuevo_restaurante == null)
				{
					System.out.println("Primero cargue los datos");
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
	public static void main(String[] args) throws NumberFormatException, FileNotFoundException, IOException
	{
		Aplicacion consola = new Aplicacion();
		consola.ejecutarOpcion();
	}
}
