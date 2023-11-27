package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.hibernate.annotations.Cascade;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor


@Entity
@Table(name = "departamentos")
@NamedQuery(name = "Departamento.findAll", query = "select d from Departamento d")

public class Departamento {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    private String nombre;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    private List<Empleado> empleados;

    @OneToOne
    @Nullable
    private Empleado jefe;

    public void removeEmpleado(Empleado e) {
     empleados.remove(e);
     e.setDepartamento(null);
    }

    /**
     * Devuelve representación de un departamento
     *
     * @return string
     */
    public String show() {
        if (id == 0) {
            return "no departamento!!!";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%2d:%-20s:", id, nombre));
        if (jefe == null || jefe.getNombre() == null) {
            sb.append("sin jefe!!");
        } else {
            sb.append(String.format("jefe [%2d:%s]", jefe.getId(), jefe.getNombre()));
        }

        return sb.toString();
    }


    public boolean isNull() {
        return this != null;
    }
}
