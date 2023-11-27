package view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import controller.DepartamentoController;
import controller.EmpleadoController;
import controller.ProyectoController;
import io.IO;
import model.Departamento;
import model.Empleado;
import model.Proyecto;

public class MenuEmpleado {

    public static void menu() {
        EmpleadoController dao = new EmpleadoController();
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

    private static void borrar(EmpleadoController dao) {
        IO.print("Código ? ");
        Integer id = IO.readInt();
        Empleado e = (Empleado) dao.getEmpleadoId(id).get();
        boolean borrado = dao.deleteEmpleado(e);
        IO.println(borrado ? "Borrado" : "No se ha podido borrar");
    }

    private static void anadir(EmpleadoController dao) {
        DepartamentoController daoD = new DepartamentoController();
        ProyectoController daoP = new ProyectoController();


        IO.print("Nombre ? ");
        String nombre = IO.readString();

        IO.print("Salario ? ");
        Double salario = IO.readDoubleOrNull();

        IO.print("Nacido (aaaa-mm-dd) ? ");
        LocalDate nacido = IO.readLocalDateOrNull();

        IO.print("Departamentos: ");
        MenuDepartamento.mostrar(daoD);
        IO.print("Selecciona el id del departamento");
        Integer depId = IO.readIntOrNull();
        Departamento d1= null;
        if (depId!= null) {
             d1 = daoD.getDepartamentoId(IO.readInt()).get();
        }
        IO.print("Proyecto id:");
        MenuProyecto.mostrar(daoP);
        IO.print("Selecciona el proyecto por su id");
        Integer pId = IO.readIntOrNull();
        Proyecto p1 = null;
        if(pId != null){
            p1 = daoP.getProyectoId(pId).get();
        }

        Empleado e = Empleado.builder()
                .nombre(nombre)
                .salario(salario)
                .nacido(nacido)
                .departamento(d1)
                .build();
        if(pId!=null){
        e.addProyecto(p1);}
        Empleado anadido = dao.createEmpleado(e);
        IO.println(anadido.isNull() ? "Añadido" : "No se ha podido añadir");
    }

    private static void modificar(EmpleadoController dao) {

        DepartamentoController daoD = new DepartamentoController();
        ProyectoController daoP = new ProyectoController();

        IO.print("Código del empleado a modificar ? ");
        mostrar(dao);
        Integer id = IO.readIntOrNull();
        Optional<Empleado> emp = dao.getEmpleadoId(id);
        if (emp.isEmpty()) {
            IO.println("No se ha encontrado al empleado");
        }else {
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
        IO.println("Fecha");
        if (nacido != null) {
            emp.get().setNacido(nacido);
            IO.println("Saliendo");
        }
        IO.println("Departamento");
        IO.printf("Departamento [%s] ? ", emp.get().getDepartamento());
        Integer departamento = IO.readIntOrNull();
        if (departamento != null) {
            emp.get().setDepartamento(Departamento.builder().id(departamento).build());
        }
            IO.println("Proyecto");
            IO.println("Tus Proyectos: ");
            emp.get().mostrarProyectos();
            IO.println("Añadir a ");
            MenuProyecto.mostrar(daoP);
            Integer pId = IO.readIntOrNull();
            if (pId != null) {
                emp.get().addProyecto(Proyecto.builder().id(pId).build());
            }

            Empleado anadido = dao.createEmpleado(emp.get());
        IO.println(anadido.toString());
        IO.println(anadido.isNull() ? "Modificado" : "No se ha podido modificar");
        }
    }

    public static void mostrar(EmpleadoController dao) {
        for (Empleado e : dao.getEmpleados()) {
            IO.println(e.show());
        }
    }

    private static void buscarPorInicioDelNombre(EmpleadoController dao) {
        IO.print("El nombre empieza por ? ");
        String inicio = IO.readString();
        for (Empleado e : dao.getEmpleadosByNombre(inicio)) {
            IO.println(e.show());
        }
    }

    private static void buscarPorCodigo(EmpleadoController dao) {
        IO.print("Código ? ");
        Integer id = IO.readInt();
        Empleado e = dao.getEmpleadoId(id).get();
        if (e != null) {
            IO.println(e.show());
        }
    }

}

