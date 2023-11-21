package model;

import jakarta.persistence.*;
import lombok.*;

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
    @ManyToMany
    @JoinTable(
            name = "proyecto_empleado",
            joinColumns = @JoinColumn(name = "proyecto_id"),
            inverseJoinColumns = @JoinColumn(name = "empleado_id")
    )
    private List<Empleado> empleado;
    public void añadirEmpleado(Empleado e){
        if (this.empleado == null) {
            System.out.println("No se pudo añadir empleado");
        }
        this.empleado.add(e);
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
        return this==null;
    }
}
