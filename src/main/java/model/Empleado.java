package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import io.IO;
import jakarta.persistence.*;
import lombok.*;
import org.checkerframework.checker.nullness.qual.Nullable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString


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
    private List<Proyecto> proyecto = new ArrayList<Proyecto>();

    public void removeDepartamento() {
        departamento.removeEmpleado(this);
        setDepartamento(null);
    }
    public void addProyecto(Proyecto p){
        if(p != null){
        if(proyecto == null){
            proyecto = new ArrayList<>();
        }
        proyecto.add(p);
        p.addEmpleado(this);
    }
    }
    public void mostrarProyectos(){
        for (int i = 0; i < proyecto.size(); i++) {
            IO.println(proyecto.get(i).show());
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
