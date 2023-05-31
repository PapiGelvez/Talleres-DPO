package modelo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import excepciones.PrecioMaximoException;

public class Pedido {
	private static int numeroPedidos = 0;
	private int idPedido;
	private int precio_total = 0;
	private String nombreCliente;
	private String direccionCliente;
	private ArrayList<Producto> productos_del_pedido = new ArrayList<>();
	private int caloriasTotales = 0;
	
	public Pedido(String nombreCliente, String direccionCliente)
	{
		this.nombreCliente = nombreCliente;
		this.direccionCliente = direccionCliente;
		numeroPedidos += 1;
		idPedido = numeroPedidos;
	}
	public int getIdPedido()
	{
		return idPedido;
	}
	public void setIdPedido(int id)
	{
		this.idPedido = id;
	}
	public void agregarProducto(Producto nuevoitem) throws PrecioMaximoException
	{
		precio_total += nuevoitem.getPrecio();
		
		if (precio_total>=150000) {
			PrecioMaximoException ex = new PrecioMaximoException(precio_total-nuevoitem.getPrecio());
			precio_total -= nuevoitem.getPrecio();
			throw ex;
		} else {
			caloriasTotales += nuevoitem.getCalorias();
			productos_del_pedido.add(nuevoitem);
		}
	}
	public ArrayList<Producto> getProductosDelPedido()
	{
		return productos_del_pedido;
	}
	public int getPrecioNetoPedido()
	{
		int precio_neto = (int) (precio_total * 0.81);
		return precio_neto;
	}
	public int getPrecioTotalPedido()
	{
		return precio_total;
	}
	public int getPrecioIVAPedido()
	{
		int precio_iva = (int) (precio_total * 0.19);
		return precio_iva;
	}
	public int getCaloriasTotalesPedido()
	{
		return caloriasTotales;
	}
	private String crearTextoFactura()
	{
		String factura_final = "";
		factura_final += "ID de factura : " + Integer.toString(getIdPedido()) + "\n";
		factura_final += "Nombre: " + nombreCliente + "\n";
		factura_final += "Dirección: " + direccionCliente + "\n";
		factura_final += "Calorias Totales Pedido: " + Integer.toString(getCaloriasTotalesPedido()) + "\n";
		for (Producto i: productos_del_pedido)
		{
			factura_final += i.generarTextoFactura();
		}
		factura_final += "Valor neto total: " + Integer.toString(getPrecioNetoPedido()) + "\n";
		factura_final += "Valor IVA total: " + Integer.toString(getPrecioIVAPedido()) + "\n";
		factura_final += "Valor total (neto + IVA): " + Integer.toString(getPrecioTotalPedido()) + "\n";
		return factura_final;
	}
	public void guardarFactura(File archivo)
	{
		FileWriter escritor;
		try {
			escritor = new FileWriter(archivo);
			escritor.write(crearTextoFactura());
			escritor.close();
			System.out.println("Creación exitosa de archivo con factura. Podrá encontrarlo con el id del pedido.");
			System.out.println("\n" + "Id de la factura: " + Integer.toString(getIdPedido()) + "\n");
		} catch (IOException e) {
			System.out.println("Ocurrió un error.");
			e.printStackTrace();
		}
	}
}
