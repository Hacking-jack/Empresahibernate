package model;

import controller.ProyectoController;
import jakarta.persistence.*;
import lombok.*;
import view.MenuEmpleado;
import view.MenuProyecto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Column(nullable = false)
    private String nombre;



    @ManyToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "proyecto_empleado",
            joinColumns = @JoinColumn(name = "proyecto_id"),
            inverseJoinColumns = @JoinColumn(name = "empleado_id")
    )
    private Set<Empleado> empleado = new HashSet<Empleado>();
    public void addEmpleado(Empleado e) {
        if (empleado == null) {
            empleado = new HashSet<>();
        }

        e.getProyecto().add(this);
        this.getEmpleado().add(e);
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
