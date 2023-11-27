package model;

import jakarta.persistence.*;
import lombok.*;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.hibernate.annotations.Cascade;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"empleado", "jefe"})

@Entity
@Table(name = "departamentos")
@NamedQuery(name = "Departamento.findAll", query = "select d from Departamento d")

public class Departamento {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    private String nombre;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Empleado> empleados = new ArrayList<>();

    @OneToOne
    @Nullable
    @JoinColumn(name = "jefe_id", referencedColumnName = "id")
    private Empleado jefe;

    public void removeEmpleado(Empleado e) {
     empleados.remove(e);
     e.setDepartamento(null);
    }
    public void addJefe(Empleado jefe) {
        this.setJefe(jefe);
        jefe.setDepartamento(this);
    }
    public void addEmpleado(Empleado e) {
        if (this.getEmpleados() == null) {
            this.setEmpleados(new ArrayList<>());
        }
        this.getEmpleados().add(e);
        e.setDepartamento(this);
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
