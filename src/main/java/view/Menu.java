package view;

import db.DataDB;
import db.HibernateManager;
import io.IO;
import utils.ApplicationProperties;

import java.util.List;


public class Menu {

    public static void main(String[] args) {
        boolean start = true;
        initDataBase();

        DataDB.getEmpleadosInit();
        DataDB.getDepartamentosInit();
        DataDB.getProyectosInit();

        List<String> opciones = List.of(
                "Empleados",
                "Departamentos",
                "Proyectos",
                "Salir");

        while (start) {
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
                    start = false;
                    return;
                default:
            }
        }

    }
    private static void initDataBase() {
        // Leemos el fichero de configuración

        ApplicationProperties properties = new ApplicationProperties();
        //logger.info("Leyendo fichero de configuración..." + properties.readProperty("app.title"));
        HibernateManager hb = HibernateManager.getInstance();
        hb.open();
        hb.close();
    }


}

