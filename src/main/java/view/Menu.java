package view;

import java.util.List;

import dao.BD;
import db.DataDB;
import db.HibernateManager;
import io.IO;
import lombok.Data;

public class Menu {
	
	public static void main(String[] args) {

		List<String> opciones = List.of( 
				"Empleados", 
				"Departamentos",
				"Proyectos",
				"Salir");
		
		while (true) {
			IO.println(opciones);
			switch (Character.toUpperCase(IO.readChar())) {
			case 'E':
				MenuEmpleado.menu();
				break;
			case 'D':
				MenuDepartamento.menu();
				break;
			case 'P':
				MenuProyecto.menu();
				break;
			case 'S':
				.close();
				return;
			default:
			}
		}		
				
	}

}

