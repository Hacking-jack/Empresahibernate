package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.IO;
import jakarta.persistence.*;
import lombok.*;
import org.checkerframework.checker.nullness.qual.Nullable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "proyecto")


@Entity
@Table(name = "empleados")
@NamedQuery(name = "Empleado.findAll", query = "select e from Empleado e")

public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private Double salario;
    private LocalDate nacido;
    @Nullable
    @ManyToOne(fetch = FetchType.EAGER)
    private Departamento departamento;

    @ManyToMany(fetch = FetchType.EAGER,mappedBy = "empleado")

    private Set<Proyecto> proyecto = new HashSet<>();

    @OneToOne(mappedBy = "jefe")
    private Departamento departamentoJefe;

    public void removeDepartamento() {
        departamento.removeEmpleado(this);
        setDepartamento(null);
    }
    public void addProyecto(Proyecto p){
        if(p != null){
        if(proyecto == null){
            proyecto = new HashSet<>();
        }
        proyecto.add(p);
        if (p.getEmpleado()==null){
            p.setEmpleado(new HashSet<>());
        }
        Empleado nuevo= Empleado.builder().id(id).build();
        p.getEmpleado().add(nuevo);
    }
    }
    public void mostrarProyectos(){
            for(Proyecto proyecto1 : proyecto){
            IO.println(proyecto1.show());
            }

    }
    /**
     * Devuelve representación de un empleado
     *
     * @return string
     */
    public String show() {
        if (id == 0) {
            return "no empleado!!!";
        }

        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%2d:%-20s:%4.2f:", id, nombre, salario));
        if (nacido == null) {
            sb.append("sin fecha de nacimiento!!");
        } else {
            sb.append(String.format("%s", nacido));
        }
        sb.append(":");
        if (departamento == null || departamento.getNombre() == null) {
            sb.append("sin departamento!!");
        } else {
            sb.append(String.format("Departamento [%2d:%s]", departamento.getId(), departamento.getNombre()));
        }

        return sb.toString();
    }

    public boolean isNull() {
        return this.nombre != null;
    }
}
