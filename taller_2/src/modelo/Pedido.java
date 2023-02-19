package modelo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Pedido {
	private static int numeroPedidos = 0;
	private int idPedido;
	private int precio_total;
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
		int precio_neto = (int) (precio_total * 0.81);
		return precio_neto;
	}
	private int getPrecioTotalPedido()
	{
		for (int i = 0; i < productos_del_pedido.size(); i++)
		{
			int precio_ind = productos_del_pedido.get(i).getPrecio();
			precio_total += precio_ind;
		}
		return precio_total;
	}
	private int getPrecioIVAPedido()
	{
		int precio_iva = (int) (precio_total * 0.19);
		return precio_iva;
	}
	private String generarTextoFactura()
	{
		String factura_final = "";
		for (int i = 0; i < productos_del_pedido.size(); i++)
		{
			factura_final += productos_del_pedido.get(i).generarTextoFactura();
		}
		factura_final += "Valor neto total: " + Integer.toString(getPrecioNetoPedido());
		factura_final += "Valor IVA total: " + Integer.toString(getPrecioIVAPedido());
		factura_final += "Valor total (neto + IVA): " + Integer.toString(getPrecioTotalPedido());
		return factura_final;
	}
	public void guardarFactura(File archivo)
	{
		
		FileWriter escritor;
		try {
			escritor = new FileWriter(archivo);
			escritor.write(generarTextoFactura());
			escritor.close();
			System.out.println("Creación exitosa de archivo con factura. Podrá encontrarlo con el id del pedido.");
		} catch (IOException e) {
			System.out.println("Ocurrió un error.");
			e.printStackTrace();
		}
	}
}
