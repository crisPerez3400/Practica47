package Nulidad;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		ArrayList<EmpresaUsuario> listaEmpresas = new ArrayList<>();

		EmpresaUsuario empresa1 = new EmpresaUsuario("EMP1", "Empresa1", "Ayoze Perez");
		EmpresaUsuario empresa2 = new EmpresaUsuario("EMP1", "Empresa222", "Chaxiraxi Hernandez");
		Carta carta = new Carta();
		
		
		int seleccionMenu;
		EmpresaUsuario empresa = null;
		System.out.println("Introduzca 1 para acceder a " + empresa1.getNombreEmpresaUsuario() + 
		" Introduzca 2 para acceder a" + empresa2.getNombreEmpresaUsuario()
		+ " Introduzca 3 para salir");
		while (true) {
		
			try {
                seleccionMenu = scanner.nextInt();
                if (seleccionMenu == 1) {
                	empresa = empresa1;
                } 
                else if (seleccionMenu == 2) {
                	empresa = empresa2;
                }
                scanner.nextLine(); // Limpiar el buffer después de leer el entero
                

                while (true) {
                    System.out.println("Introduzca 1 para generar una carta, 2 para salir del programa (se borrarán los archivos temporales de Cartas)");
                    int seleccionMenu2 = scanner.nextInt();
                    switch (seleccionMenu2) {
                        case 1:
                            carta.generarCarta(empresa);
                            carta.enviarCarta();
                            break; // Agrega break al final de cada case
                        case 2:
                            carta.borrarCartas();
                            return; // Termina el programa
                        default:
                            System.out.println("Debe introducir un número del 1 al 2.");
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe introducir un número del 1 al 2.");
                scanner.next(); // Limpiar el buffer en caso de excepción
            }
 
		}
	}
		//System.out.println( empresa1.getNombreUsuario());
      //  carta.generarCarta(empresa2);
        
}
