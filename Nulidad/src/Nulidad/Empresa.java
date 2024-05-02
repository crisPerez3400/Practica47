package Nulidad;

import java.util.ArrayList;

class Empresa {
	private ArrayList<String> numerosNulidad;
	private ArrayList<Double> pagos;
	private String nombreEmpresa;
	private String nombreCliente;
	 
	public Empresa(ArrayList<String> numerosNulidad, String nombreEmpresa, String nombreCliente, ArrayList<Double> pagos) {
		this.numerosNulidad = numerosNulidad;
		this.nombreEmpresa = nombreEmpresa;
		this.nombreCliente = nombreCliente;
		this.pagos = pagos;
	}

 

 

    public String getNombreEmpresa() {
        return this.nombreEmpresa;
    }
    
    public String getNombreCliente() {
        return this.nombreCliente;
    }
    
    public String getNumerosNulidad() {
    	
        return this.numerosNulidad.toString();
    }
    
    public ArrayList<Double> getpagos() {
        return this.pagos;
    }
    
    
}
