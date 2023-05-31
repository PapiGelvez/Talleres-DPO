package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.ProductoMenu;
import modelo.Restaurante;

public class ProductoMenuTest {
	
	ProductoMenu producto;
	
	@BeforeEach
	public void setup()
	{
		producto = new ProductoMenu("Helado de chocolate", 4500, 400);

	}
	
	@Test
	public void constructorTest() {
		String nombre = "Helado de chocolate";
		int precioBase = 4500;
		int calorias = 400;
		
		ProductoMenu productotest = new ProductoMenu(nombre, precioBase, calorias);
		
		Assertions.assertEquals(nombre, productotest.getNombre(), "La prueba del nombre fallo");
		Assertions.assertEquals(precioBase, productotest.getPrecio(), "La prueba del precio fallo");
		Assertions.assertEquals(calorias, productotest.getCalorias(), "La prueba de las calorias fallo");
	}
	
	@Test
	public void getCaloriasTest() {
		int calorias = producto.getCalorias();

		Assertions.assertEquals(400, calorias, "La prueba de las calorias fallo");
	}
	
	@Test
	public void generarTextoFacturaTest() {
		String linea = "Precio del producto " + producto.getNombre() + ": " + Integer.toString(producto.getPrecio()) + "\n";
		Assertions.assertEquals(linea, producto.generarTextoFactura(), "La prueba de generar factura fallo");
	}
}
