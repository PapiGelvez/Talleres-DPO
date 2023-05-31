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
		productos_del_pedido.add(nuevoitem);
	}
	public ArrayList<Producto> getProductosDelPedido()
	{
		return productos_del_pedido;
	}
	private int getPrecioNetoPedido()
	{
		int precio_final = 0;
		for (Producto i: productos_del_pedido)
		{
			precio_final += i.getPrecio();
		}
		int precio_neto = (int) (precio_final * 0.81);
		return precio_neto;
	}
	private int getPrecioTotalPedido()
	{
		int precio_final = 0;
		for (int i = 0; i < getProductosDelPedido().size(); i++)
		{
			int precio_ind = getProductosDelPedido().get(i).getPrecio();
			precio_final += precio_ind;
		}
		return precio_final;
	}
	private int getPrecioIVAPedido()
	{
		int precio_final = 0;
		for (int i = 0; i < getProductosDelPedido().size(); i++)
		{
			int precio_ind = getProductosDelPedido().get(i).getPrecio();
			precio_final += precio_ind;
		}
		int precio_iva = (int) (precio_final * 0.19);
		return precio_iva;
	}
	private int getCaloriasTotalesPedido()
	{
		for (int i = 0; i < getProductosDelPedido().size(); i++)
		{
			int calorias_ind = getProductosDelPedido().get(i).getCalorias();
			caloriasTotales += calorias_ind;
		}
		return caloriasTotales;
	}
	private String crearTextoFactura()
	{
		String factura_final = "";
		factura_final += "ID de factura : " + Integer.toString(getIdPedido()) + "\n";
		factura_final += "Nombre: " + nombreCliente + "\n";
		factura_final += "Dirección: " + direccionCliente + "\n";
		factura_final += "Calorias Totales Pedido: " + Integer.toString(getCaloriasTotalesPedido()) + "\n";
		for (int i = 0; i < getProductosDelPedido().size(); i++)
		{
			factura_final += getProductosDelPedido().get(i).generarTextoFactura();
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
			System.out.println("Id de la factura: " + Integer.toString(getIdPedido()));
		} catch (IOException e) {
			System.out.println("Ocurrió un error.");
			e.printStackTrace();
		}
	}
	public int getPrecio_total() {
		return precio_total;
	}
	public void setPrecio_total(int precio_total) {
		this.precio_total = precio_total;
	}
}
