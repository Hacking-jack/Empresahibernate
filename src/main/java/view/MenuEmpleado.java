package view;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import controller.EmpresaController;
import dao.EmpleadoDao;
import io.IO;
import model.Departamento;
import model.Empleado;

public class MenuEmpleado {
	
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
			IO.println("Empleados: " + opciones);
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
		Empleado e = (Empleado) dao.getEmpleadoId(id).get();
		boolean borrado = dao.deleteEmpleado(e);
		IO.println(borrado ? "Borrado" : "No se ha podido borrar");
	}

	private static void anadir(EmpresaController dao) {
		IO.print("Nombre ? ");
		String nombre = IO.readString();
		IO.print("Salario ? ");
		Double salario = IO.readDoubleOrNull();
		IO.print("Nacido (aaaa-mm-dd) ? ");
		LocalDate nacido = IO.readLocalDateOrNull();
		Empleado e = Empleado.builder()
				.nombre(nombre)
				.salario(salario)
				.nacido(nacido)
				.build();
		Empleado anadido = dao.createEmpleado(e);
		IO.println(anadido.isNull() ? "Añadido" : "No se ha podido añadir");
	}

	private static void modificar(EmpresaController dao) {
		IO.print("Código del empleado a modificar ? ");
		Integer id = IO.readInt();
		Optional<Empleado> emp = dao.getEmpleadoId(id);
		if (emp == null) {
			IO.println("No se ha encontrado al empleado");
			return;
		}
		IO.printf("Nombre [%s] ? ", emp.get().getNombre());
		String nombre = IO.readString();
		if (!nombre.isBlank()) {
			emp.get().setNombre(nombre);
		}
		IO.printf("Salario [%s] ? ", emp.get().getSalario());
		Double salario = IO.readDoubleOrNull();
		if (salario != null) {
			emp.get().setSalario(salario);
		}
		IO.printf("Nacido (aaaa-mm-dd) [%s] ? ", emp.get().getNacido());
		LocalDate nacido = IO.readLocalDateOrNull();
		if (nacido != null) {
			emp.get().setNacido(nacido);
		}
		IO.printf("Departamento [%s] ? ", emp.get().getDepartamento().show());
		Integer departamento = IO.readIntOrNull();
		if (departamento != null) {
			emp.get().setDepartamento(Departamento.builder().id(departamento).build());
		}
		Empleado anadido = dao.createEmpleado(emp.get());
		IO.println(anadido.isNull() ? "Modificado" : "No se ha podido modificar");
	}

	private static void mostrar(EmpresaController dao) {
		for (Empleado e : dao.getEmpleados()) {
			IO.println(e.show());
		}
	}

	private static void buscarPorInicioDelNombre(EmpresaController dao) {
		IO.print("El nombre empieza por ? ");
		String inicio = IO.readString();
		for (Empleado e : dao.getEmpleadosByNombre(inicio)) {
			IO.println(e.show());
		}
	}

	private static void buscarPorCodigo(EmpresaController dao) {
		IO.print("Código ? ");
		Integer id = IO.readInt();
		Empleado e = dao.getEmpleadoId(id).get();
		if (e != null) {
			IO.println(e.show());
		}
	}

}

