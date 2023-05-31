package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import excepciones.PrecioMaximoException;
import modelo.Combo;
import modelo.Pedido;
import modelo.Producto;
import modelo.ProductoMenu;

class PedidoTest {

	Pedido pedido;

	ProductoMenu producto1;
	ProductoMenu producto2;
	ProductoMenu producto3;

	@BeforeEach
	public void setUp() throws PrecioMaximoException {
		pedido = new Pedido("Nombre", "Direccion");

		producto1 = new ProductoMenu("Hamburguesa triple", 30000, 600);
		
		producto2 = new ProductoMenu("Gaseosa grande", 8000, 500);
		
		producto3 = new ProductoMenu("Papas gigantes", 10000, 550);

		pedido.agregarProducto(producto1);
		pedido.agregarProducto(producto2);
		pedido.agregarProducto(producto3);

	}
	
	@Test
	public void getItemsTest() {
		ArrayList<Producto> items = pedido.getProductosDelPedido();

		Assertions.assertEquals(3, items.size());
		Assertions.assertEquals(producto1, items.get(0));
		Assertions.assertEquals(producto2, items.get(1));
		Assertions.assertEquals(producto3, items.get(2));
	}
	
	@Test
	public void agregarProductoTest()  {
		ProductoMenu producto4 = new ProductoMenu("Helado de vainilla", 5000, 350);
		try {
			pedido.agregarProducto(producto4);
		} catch (PrecioMaximoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Producto> items = pedido.getProductosDelPedido();
		
		Assertions.assertEquals(4, items.size());
		Assertions.assertEquals(producto4, items.get(3));
	}
	
	@Test
	public void agregarProductoPrecioExceptionTest() {

		ProductoMenu producto5 = new ProductoMenu("ProdCaro", 150000, 50000);
		
		Assertions.assertThrows(PrecioMaximoException.class, () -> {
			pedido.agregarProducto(producto5);
		});
	}
	
	@Test
	public void getCaloriasTotalesTest() throws PrecioMaximoException {
		int caloriasTotales = pedido.getCaloriasTotalesPedido();

		Assertions.assertEquals(1650, caloriasTotales);
	}
	@Test
	public void getPrecioNetoPedidoTest() {
		int precioNetoPedido = pedido.getPrecioNetoPedido();
		
		Assertions.assertEquals(38880, precioNetoPedido);
	}
	@Test
	public void getPrecioTotalPedidoTest() {

		int precioTotalPedido = pedido.getPrecioTotalPedido();

		Assertions.assertEquals(48000, precioTotalPedido);
	}
	@Test
	public void getPrecioIVAPedidoTest() {

		int precioIVAPedido = pedido.getPrecioIVAPedido();

		Assertions.assertEquals(9120, precioIVAPedido);
	}

}
