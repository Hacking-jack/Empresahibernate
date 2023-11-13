package view;

import java.util.List;


import io.IO;


public class Menu {

    public static void main(String[] args) {
        boolean start = true;
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

}

