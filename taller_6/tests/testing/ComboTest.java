package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.Combo;
import modelo.Producto;
import modelo.ProductoMenu;

class ComboTest {
	Combo combo;
	ProductoMenu producto;
	
	@BeforeEach
	public void setUp(){
		combo = new Combo("Super combo", 20.0);
		producto = new ProductoMenu("Hamburguesa triple", 30000, 600);
		combo.agregarItemACombo(producto);
		
		producto = new ProductoMenu("Gaseosa grande", 8000, 500);
		combo.agregarItemACombo(producto);
		
		producto = new ProductoMenu("Papas gigantes", 10000, 550);
		combo.agregarItemACombo(producto);
	}
	
	@Test
	public void getPrecioTest() {
		int precioCombo = combo.getPrecio();

		Assertions.assertEquals(38400, precioCombo);
	}
	
	@Test
	public void getCaloriasTest() {
		int caloriasCombo = combo.getCalorias();
		
		Assertions.assertEquals(1650, caloriasCombo);
	}
	
	@Test
	public void generarTextoFacturaTest() {
		String linea = "Precio del " + combo.getNombre() + ": " + Integer.toString(combo.getPrecio()) + "\n";
		Assertions.assertEquals(linea, combo.generarTextoFactura(), "La prueba de generar factura fallo");
	}
}
