package model;

import controller.ProyectoController;
import jakarta.persistence.*;
import lombok.*;
import view.MenuEmpleado;
import view.MenuProyecto;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor


@Entity
@Table(name= "proyectos")
@NamedQuery(name = "Proyecto.findAll", query = "SELECT p FROM Proyecto p")
public class Proyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;

    private String nombre;



    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name = "proyecto_empleado",
            joinColumns = @JoinColumn(name = "proyecto_id"),
            inverseJoinColumns = @JoinColumn(name = "empleado_id")
    )
    private List<Empleado> empleado = new ArrayList<Empleado>();
    public void addEmpleado(Empleado e) {
        if (empleado == null) {
            empleado = new ArrayList<>();
        }

        if (!empleado.contains(e)) {
            empleado.add(e);
            e.getProyecto().add(this);
        }
        new ProyectoController().createProyecto(this);
    }

    public void removeEmpleado(Empleado e) {
        empleado.remove(e);
        e.getProyecto().remove(this);
    }


    public String show() {
        if (id == 0) {
            return "no proyecto!!!";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%2d:%-20s:", id, nombre));

        return sb.toString();
    }

    public boolean isNull(){
        return this!=null;
    }
}
