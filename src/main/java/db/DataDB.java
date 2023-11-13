package db;

import controller.EmpleadoController;
import model.Departamento;
import model.Empleado;
import model.Proyecto;

import java.time.LocalDate;
import java.util.List;

public final class DataDB {
   private static EmpleadoController dao = new EmpleadoController();
    public static List<Empleado> getEmpleadosInit() {
        return List.of(
               Empleado.builder().nombre("Juan").salario(1000.0).nacido(LocalDate.of(2000, 1, 1)).build()
        );
    }
    public static List<Departamento> getDepartamentosInit() {

        return List.of(
                Departamento.builder().nombre("Informatica").build()



        );

    }

    public static List<Proyecto> getProyectosInit() {
        return List.of(
                new Proyecto().builder().nombre("Comenzamos").build()


        );

    }

}
