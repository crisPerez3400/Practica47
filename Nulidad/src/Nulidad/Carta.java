package Nulidad;
import java.io.File;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import Nulidad.EmpresaUsuario;
public class Carta<E> {
	
	void generarCarta(EmpresaUsuario empresaUsuario) {
		Nulidad nulidad = new Nulidad(); //INSTANCIA NULIDAD
		Empresa empresa = nulidad.leerCodTrans();
		String empresaEscribir = empresa.getNombreEmpresa();
		String NombreCliente = empresa.getNombreCliente();		
		String nulidadEscribir = empresa.getNumerosNulidad();
		ArrayList<Double> pagosEscribir = empresa.getpagos();
		String pagosString = pagosEscribir.toString();
		String suNombre = empresaUsuario.getNombreUsuario();
		String suEmpresa = empresaUsuario.getNombreEmpresaUsuario();
		
		
		//SUMAR PAGOS
		
		double sumaPagos = calcularSumaPagos(pagosEscribir); //total sumapagos
        String pagosTotal = String.valueOf(sumaPagos);
	    
	    //QUITAR CODIGO CONTACTO
	    String regex = "^Contacto\\d+, ";
	    Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(NombreCliente);
        String NombreClienteFormat = matcher.replaceAll("");

	
		
        String archivoDestino = "Cartas\\CartasGuardadas\\ModeloCarta2.txt";
        
        String cartaModificar = convertirTxtAString("Cartas\\Modelos\\ModeloCarta2.txt");

        cartaModificar = reemplazarPalabras(cartaModificar, empresaEscribir, NombreClienteFormat, nulidadEscribir, pagosString, pagosTotal, suNombre, suEmpresa);
        System.out.println(cartaModificar);
        
        crearArchivoEscribir(cartaModificar, empresaEscribir);
        
	}
	 private double calcularSumaPagos(ArrayList<Double> pagos) {
	        double sumaPagos = 0.0;
	        for (double pago : pagos) {
	            sumaPagos += pago;
	        }
	        return sumaPagos;
	    }
	
	//convertir txt a string para reemplazar los datos correspondientes
	    public String convertirTxtAString(String rutaArchivo) {
	        StringBuilder contenido = new StringBuilder();

	        try {
	            File archivo = new File(rutaArchivo);
	            Scanner scanner = new Scanner(archivo);

	            while (scanner.hasNextLine()) {
	                contenido.append(scanner.nextLine());
	                contenido.append("\n"); // Agrega un salto de línea después de cada línea leída
	            }

	            scanner.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return contenido.toString();
	    }
	    
	    //reemplazar palabras	
	    public String reemplazarPalabras(String textoCarta, String empresa, String nombreCliente, String nulidad, String pagos, String totalPago, String suNombre, String suEmpresa){
	    	textoCarta = textoCarta.replace("[Nombre_cliente]", nombreCliente);
	    	textoCarta = textoCarta.replace("[Numero_nulidad]", nulidad);
	    	textoCarta = textoCarta.replace("[nombre_empresa]", empresa);
	    	textoCarta = textoCarta.replace("[Lista_pago_servicios]", "Codigo: " + nulidad + "\n" +  "Pago:" + pagos);
	    	textoCarta = textoCarta.replace("[total_servicios]", totalPago + "€");
	    	textoCarta = textoCarta.replace("[Su_nombre]", suNombre);
	    	textoCarta = textoCarta.replace("[Su_empresa]", suEmpresa);

			return textoCarta;
	    	
	    }
	
	
	
	//CONVERTIR STRING A txt
	    public static void crearArchivoEscribir(String texto, String NombreEmpresa) {
	        try {
	            String rutaArchivo = "Cartas\\CartasGuardadas\\carta_Nulidad_" + NombreEmpresa + ".txt";
	            FileWriter writer = new FileWriter(rutaArchivo);

	            BufferedWriter bw = new BufferedWriter(writer);

	            bw.write(texto);

	            bw.close();
	            writer.close();

	            System.out.println("Se ha creado el archivo correctamente.");
	        } catch (IOException e) {
	            System.err.println("Error al crear el archivo: " + e.getMessage());
	        }
	    }
	   
	    public void enviarCarta() {
	    	System.out.println("La carta ha sido enviada");
	    }
	    
	    //BORRAR CARTAS
	    
	    public void borrarCartas() {
	    	File carpeta = new File("Cartas\\CartasGuardadas");
	    	
	    	if (carpeta.isDirectory()) {
	    		File[] archivos = carpeta.listFiles();
	    		
	    	for (File archivo : archivos) {
	    		archivo.delete();
	    	}
	    	}
	    }
	    
}
