package Nulidad;

import java.io.FileInputStream;


import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import org.apache.commons.compress.harmony.unpack200.bytecode.forms.ThisFieldRefForm;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.util.Scanner;
import java.util.Scanner;

public class Nulidad {

	Scanner scanner = new Scanner(System.in);

	public void leerArchivoExcel() {
		try {
			FileInputStream f = new FileInputStream("src\\Nulidad.xlsx");

			XSSFWorkbook libro = new XSSFWorkbook(f);

			XSSFSheet hoja = libro.getSheetAt(0);

			for (Row row : hoja) {
				for (Cell cell : row) {
					switch (cell.getCellType()) {
					case STRING:
						System.out.print(cell.getStringCellValue() + "\t");
						break;
					case NUMERIC:
						System.out.print(cell.getNumericCellValue() + "\t");
						break;
					case BOOLEAN:
						System.out.print(cell.getBooleanCellValue() + "\t");
						break;
					default:
						System.out.print(" \t");
					}
				}
				System.out.println(); // Salto de línea después de cada fila
			}
			f.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Empresa leerCodTrans() {
		System.out.println("Inserte nombre de la empresa");
		String columnaABuscar = scanner.nextLine();
		//String columnaABuscar = "Empresa005";
		String nomEmpresa = null;
		String nombrePersona = null;
		ArrayList<Double> pagos = new ArrayList<>();
		ArrayList<String> numerosNulidadAnadir = new ArrayList<>(); // ArrayList para almacenar los valores de la
		boolean filaEncontrada = false;
											// primera columna
		try {
			FileInputStream f = new FileInputStream("src\\Nulidad.xlsx");
			XSSFWorkbook libro = new XSSFWorkbook(f);
			XSSFSheet hoja = libro.getSheetAt(0);

			for (Row row : hoja) { //iteracion filas
				Cell cell = row.getCell(1); // Suponiendo que el valor a buscar está en la primera columna (intcolumna
											// 0)
                
				if (cell != null && cell.getStringCellValue() //busca coincidencia en la columna
						.equals(columnaABuscar)) {
					filaEncontrada = true;
					nomEmpresa = row.getCell(1).getStringCellValue();
					nombrePersona = row.getCell(8).getStringCellValue();
					Cell primeraColumnaCell = row.getCell(0);
					Cell columnaPagos = row.getCell(2);
					
					if (primeraColumnaCell != null) {
						switch (primeraColumnaCell.getCellType()) {
						case STRING:
							String valorCeldaTexto = primeraColumnaCell.getStringCellValue();
							numerosNulidadAnadir.add(valorCeldaTexto);
							
							break;
						case NUMERIC:
							double valorCeldaNumerica = primeraColumnaCell.getNumericCellValue();
							int valorFormateado = (int) valorCeldaNumerica;
							numerosNulidadAnadir.add(String.valueOf(valorFormateado));

							break;
						default:
							numerosNulidadAnadir.add(null); // Valor nulo si no es ni texto ni numérico
						}
						
					
					}
					
	                if (columnaPagos != null && columnaPagos.getCellType() == CellType.NUMERIC) {
	                    double valorPago = columnaPagos.getNumericCellValue();
	                    pagos.add(valorPago);
	                }
				}

			}
			// numerosNulidadAnadir.add(nomEmpresa);
			// numerosNulidadAnadir.add(nombrePersona);

			f.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (filaEncontrada) {

			Empresa empresa = new Empresa(numerosNulidadAnadir, nomEmpresa, nombrePersona, pagos);
			return empresa;
		} else {
			System.out.println("No se encontraron filas con el valor especificado.");
			return null; // Devuelve un ArrayList vacío si no se encontraron filas
		}
	}

}
