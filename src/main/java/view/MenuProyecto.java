package view;

import controller.DepartamentoController;
import controller.EmpleadoController;
import controller.ProyectoController;
import io.IO;
import model.Departamento;
import model.Empleado;
import model.Proyecto;

import java.util.List;
import java.util.Optional;

public class MenuProyecto {

    public static void menu() {

        ProyectoController dao = new ProyectoController();

        List<String> opciones = List.of(
                "buscar por Código",
                "buscar por Nombre",
                "Mostrar",
                "Añadir",
                "modiFicar",
                "Eliminar",
                "Salir");

        while (true) {
            IO.println("Proyectos: " + opciones);
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

    private static void borrar(ProyectoController dao) {
        IO.print("Código ? ");
        Integer id = IO.readInt();
        Proyecto d = (Proyecto) dao.getProyectoId(id).get();
        boolean borrado = dao.deleteProyecto(d);
        IO.println(borrado ? "Borrado" : "No se ha podido borrar");
    }

    public static void anadir(ProyectoController dao) {
        IO.print("Nombre ? ");
        String nombre = IO.readString();
        Proyecto d = Proyecto.builder().nombre(nombre).build();
        Proyecto anadido = dao.createProyecto(d);
        IO.println(anadido.isNull() ? "Añadido" : "No se ha podido añadir");
    }

    private static void modificar(ProyectoController dao) {
        IO.print("Código del departamento a modificar ? ");
        EmpleadoController daoE = new EmpleadoController();
        DepartamentoController daoD = new DepartamentoController();
        Integer id = IO.readInt();
        Optional<Proyecto> d = dao.getProyectoId(id);
        if (d == null) {
            IO.println("No se ha encontrado el proyecto");
            return;
        }
        IO.printf("Nombre [%s] ? ", d.get().getNombre());
        String nombre = IO.readString();
        if (!nombre.isBlank()) {
            d.get().setNombre(nombre);
        }
        Proyecto anadido = dao.createProyecto(d.get());
        IO.println(anadido.isNull() ? "Modificado" : "No se ha podido modificar");
    }

    public static void mostrar(ProyectoController dao) {
        for (Proyecto d : dao.getProyectos()) {
            IO.println(d.show());
        }
    }

    private static void buscarPorInicioDelNombre(ProyectoController dao) {
        IO.print("El nombre empieza por ? ");
        String inicio = IO.readString();
        for (Proyecto d : dao.getProyectosByNombre(inicio)) {
            IO.println(d.show());
        }
    }

    private static void buscarPorCodigo(ProyectoController dao) {
        DepartamentoController daoD = new DepartamentoController();
        EmpleadoController daoE = new EmpleadoController();
        IO.print("Código ? ");
        Integer id = IO.readInt();
        Optional<Proyecto> p = dao.getProyectoId(id);
        if (!p.isEmpty()) {
            IO.println(p.get().show());

        }
    }

}
