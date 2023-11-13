package view;

import java.util.List;
import java.util.Optional;

import controller.EmpresaController;
import io.IO;
import model.Departamento;
import model.Empleado;

public class MenuDepartamento {
	
	public static void menu() {

		EmpresaController dao = new EmpresaController();

		List<String> opciones = List.of(
				"buscar por Código", 
				"buscar por Nombre", 
				"Mostrar", 
				"Añadir",
				"modiFicar",
				"Eliminar",
				"Salir");
		
		while (true) {
			IO.println("Departamentos: " + opciones);
			switch (Character.toUpperCase(IO.readChar())) {
			case 'C':
				buscarPorCodigo(dao);
				break;
			case 'N':
				buscarPorInicioDelNombre(dao);
				break;
			case 'M':
				mostrar(dao);
				break;
			case 'A':
				anadir(dao);
				break;
			case 'F':
				modificar(dao);
				break;
			case 'E':
				borrar(dao);
				break;
			case 'S':
				return;
			default:
			}
		}		
		
	}

	private static void borrar(EmpresaController dao) {
		IO.print("Código ? ");
		Integer id = IO.readInt();
		Departamento d = (Departamento) dao.getDepartamentoId(id).get();
		boolean borrado = dao.deleteDepartamento(d);
		IO.println(borrado ? "Borrado" : "No se ha podido borrar");
	}

	private static void anadir(EmpresaController dao) {
		IO.print("Nombre ? ");
		String nombre = IO.readString();		
		Departamento d = Departamento.builder().nombre(nombre).build();		
		Departamento anadido = dao.crearDepartamento(d);
		IO.println(anadido.isNull() ? "Añadido" : "No se ha podido añadir");
	}

	private static void modificar(EmpresaController dao) {
		IO.print("Código del departamento a modificar ? ");
		Integer id = IO.readInt();
		Optional<Departamento> d = dao.getDepartamentoId(id);
		if (d == null) {
			IO.println("No se ha encontrado el departamento");		
			return;
		}
		IO.printf("Nombre [%s] ? ", d.get().getNombre());
		String nombre = IO.readString();
		if (!nombre.isBlank()) {
			d.get().setNombre(nombre);
		}
		IO.printf("Jefe [%s] ? ", d.get().getJefe().show());
		Integer jefe = IO.readIntOrNull();
		if (jefe != null) {
			EmpresaController daoEmpleado = new EmpresaController();
			d.get().setJefe(daoEmpleado.getEmpleadoId(jefe).get());
		}
		Departamento anadido = dao.crearDepartamento(d.get());
		IO.println(anadido.isNull() ? "Modificado" : "No se ha podido modificar");
	}

	private static void mostrar(EmpresaController dao) {
		for (Departamento d : dao.getDepartamentos()) {
			IO.println(d.show());
		}
	}

	private static void buscarPorInicioDelNombre(EmpresaController dao) {
		IO.print("El nombre empieza por ? ");
		String inicio = IO.readString();
		for (Departamento d : dao.getDepartamentosByNombre(inicio)) {
			IO.println(d.show());
		}
	}

	private static void buscarPorCodigo(EmpresaController dao) {
		IO.print("Código ? ");
		Integer id = IO.readInt();
		Optional<Departamento> d = dao.getDepartamentoId(id);
		if (d != null) {
			IO.println(d.get().show());
			IO.println("* Empleados del departamento :");
			for (Empleado e : dao.getEmpleados()) {
				IO.println(e.show());
			}
		}
	}

}
