package modelo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Pedido {
	private static int numeroPedidos = 0;
	private int idPedido;
	private int precio_total = 0;
	private String nombreCliente;
	private String direccionCliente;
	private ArrayList<Producto> productos_del_pedido = new ArrayList<>();
	
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
	public void agregarProducto(Producto nuevoitem)
	{
		productos_del_pedido.add(nuevoitem);
	}
	public ArrayList<Producto> getProductosDelPedido()
	{
		return productos_del_pedido;
	}
	private int getPrecioNetoPedido()
	{
		int precio_final = 0;
		for (int i = 0; i < getProductosDelPedido().size(); i++)
		{
			int precio_ind = getProductosDelPedido().get(i).getPrecio();
			precio_final += precio_ind;
		}
		int precio_neto = (int) (precio_final * 0.81);
		return precio_neto;
	}
	private int getPrecioTotalPedido()
	{
		for (int i = 0; i < getProductosDelPedido().size(); i++)
		{
			int precio_ind = getProductosDelPedido().get(i).getPrecio();
			precio_total += precio_ind;
		}
		return precio_total;
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
	private String crearTextoFactura()
	{
		String factura_final = "";
		factura_final += "ID de factura : " + Integer.toString(getIdPedido()) + "\n";
		factura_final += "Nombre: " + nombreCliente + "\n";
		factura_final += "Dirección: " + direccionCliente + "\n";
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
}
