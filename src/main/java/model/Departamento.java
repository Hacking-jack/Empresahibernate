package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.nullness.qual.Nullable;

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
    private Integer id;
    private String nombre;
    @OneToOne
    @Nullable
    private Empleado jefe;

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
        return this==null;
    }
}
