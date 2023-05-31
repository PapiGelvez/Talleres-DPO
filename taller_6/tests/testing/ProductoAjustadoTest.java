package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.Ingrediente;
import modelo.Producto;
import modelo.ProductoAjustado;
import modelo.ProductoMenu;
import modelo.Restaurante;

import java.util.ArrayList;

class ProductoAjustadoTest {

	ProductoAjustado producto_ajustado;
	ProductoMenu base;
	Restaurante restaurante;
	
	@BeforeEach
	public void setup()
	{
		ArrayList<Ingrediente> ingredientes_eliminados = new ArrayList<>();
		ArrayList<Ingrediente> ingredientes_agregados = new ArrayList<>();
		
		Restaurante.getIngredientes().add(new Ingrediente("aros de cebolla", 5000, 150));
		Restaurante.getIngredientes().add(new Ingrediente("tartara", 2000, 100));
		Restaurante.getIngredientes().add(new Ingrediente("huevo", 2500, 70));
		Restaurante.getIngredientes().add(new Ingrediente("piña", 2500, 50));
		
		base = new ProductoMenu("nadaterreno", 30000, 500);

		//ingredientes_eliminados.add(new Ingrediente("aros de cebolla", 5000, 150));
		//ingredientes_eliminados.add(new Ingrediente("tartara", 2000, 100));

		//ingredientes_agregados.add(new Ingrediente("repollo", 3000, 50));
		//ingredientes_agregados.add(new Ingrediente("salchicha", 5000, 250));
		
		producto_ajustado = new ProductoAjustado(base);
		
		producto_ajustado.eliminar_ingrediente("aros de cebolla");
		producto_ajustado.eliminar_ingrediente("tartara");
		producto_ajustado.agregar_ingrediente("repollo");
		producto_ajustado.agregar_ingrediente("salchicha");
	}
	
	@Test
	public void getCaloriasTest() {
		int calorias = producto_ajustado.getCalorias();

		Assertions.assertEquals(550, calorias);
	}
	
	@Test
	public void getPrecioTest() {
		int precio = producto_ajustado.getPrecio();

		Assertions.assertEquals(31000, precio);
	}
}
