package db;

import model.Departamento;
import model.Empleado;
import model.Proyecto;

import java.time.LocalDate;
import java.util.List;

public final class DataDB {
    public static List<Empleado> getEmpleadosInit() {
        return List.of(
               new Empleado(),
               new Empleado(),
               new Empleado()
        );
    }
    public static List<Departamento> getDepartamentosInit() {
        return List.of(
                new Departamento(),
                new Departamento(),
                new Departamento()

        );

    }

    public static List<Proyecto> getProyectosInit() {
        return List.of(
                new Proyecto(),
                new Proyecto()

        );

    }

}
