package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Proyectos {
    Integer id ;
    String nombre;
    public String show() {
        if (id == 0) {
            return "no proyecto!!!";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%2d:%-20s:", id, nombre));

        return sb.toString();
    }
}